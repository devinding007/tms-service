// ファイル: jp.co.basenet.weolab.tms_service.proposal.service.ProposalService.java

package jp.co.basenet.weolab.tms_service.proposal.service;

import jp.co.basenet.weolab.tms_service.common.dto.ApiListResult;
import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseResult;
import jp.co.basenet.weolab.tms_service.personel.dto.SkillScore;
import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;
import jp.co.basenet.weolab.tms_service.personeltalents.service.TalentService;
import jp.co.basenet.weolab.tms_service.proposal.dto.Proposal;
import jp.co.basenet.weolab.tms_service.proposal.entity.ProposalDoc;
import jp.co.basenet.weolab.tms_service.proposal.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 要員提案サービス（MongoDB 版）
 * - 提案の保存（新規作成・更新）
 * - 提案一覧取得（ページネーション対応）
 * - 提案削除
 */
@Service
@Transactional
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private TalentService talentService;

    /**
     * 提案一覧をページネーション付きで取得
     */
    @Transactional(readOnly = true)
    public ApiListResult<Proposal> listProposals(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProposalDoc> pageResult = proposalRepository.findAll(pageable);

        List<Proposal> proposals = pageResult.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        ApiListResult<Proposal> result = new ApiListResult<>();
        result.setItems(proposals);
        result.setTotal(pageResult.getTotalElements());
        return result;
    }

    /**
     * 提案を保存（新規作成 or 更新）
     */
    @Transactional
    public Proposal saveProposal(Proposal dto) {
        ProposalDoc doc = new ProposalDoc();
        String proposalId = dto.get提案ID();
        if (proposalId == null || proposalId.isEmpty()) {
            proposalId = java.util.UUID.randomUUID().toString();
        }
        doc.setProposalId(proposalId);
        doc.setProposalName(dto.get提案名());
        doc.setJobDescription(dto.get募集要項());
        doc.setUpdatedAt(LocalDateTime.now());

        // 既存ドキュメントがあれば createdAt を維持
        proposalRepository.findById(proposalId)
                .ifPresent(existing -> doc.setCreatedAt(existing.getCreatedAt()));

        // 候補人材と分析結果を内嵌（詳細情報を取得）
        if (dto.get対象人材() != null) {
            List<ProposalDoc.Candidate> candidates = dto.get対象人材().stream()
                    .map(talent -> {
                        // ★★★ TalentService から詳細を取得 ★★★
                        TalentDTO fullTalent = talentService.getTalentById(talent.getTalentId());
                        if (fullTalent == null) {
                            throw new RuntimeException("人材が見つかりません: " + talent.getTalentId());
                        }

                        ProposalDoc.Candidate candidate = new ProposalDoc.Candidate();
                        // 人材詳細を内嵌
                        candidate.setTalentId(fullTalent.getTalentId());
                        candidate.setCompany(fullTalent.getCompany());
                        candidate.setName(fullTalent.getName());
                        candidate.setEmployeeNumber(fullTalent.getEmployeeNumber());
                        candidate.setBirthDate(fullTalent.getBirthDate());
                        candidate.setProjectEndDate(fullTalent.getProjectEndDate());
                        candidate.setBpFlag(fullTalent.getBpFlag());
                        candidate.setDeletedFlag(fullTalent.getDeletedFlag());

                        // 分析結果をマッピング
                        ProposalAnalyseResult analysis = findAnalysis(dto.get分析結果(), talent.getTalentId());
                        if (analysis != null) {
                            candidate.setMatchScore(analysis.getMatchRate() != null ? analysis.getMatchRate().shortValue() : 0);
                            candidate.setComment(analysis.getReason() != null ? analysis.getReason() : "分析結果なし");
                            candidate.setSkillScores(analysis.getSkills() != null ?
                                    List.of(analysis.getSkills()).stream()
                                            .map(skill -> {
                                                ProposalDoc.SkillScore score = new ProposalDoc.SkillScore();
                                                score.setSkillName(skill.getSkillName());
                                                score.setScore(skill.getScore());
                                                return score;
                                            })
                                            .collect(Collectors.toList()) :
                                    List.of());
                            candidate.setAnalyzedAt(LocalDateTime.now());
                        }
                        return candidate;
                    })
                    .collect(Collectors.toList());
            doc.setCandidates(candidates);
        }

        ProposalDoc saved = proposalRepository.save(doc);
        return convertToDto(saved);
    }

    /**
     * 提案を取得（詳細）
     */
    @Transactional(readOnly = true)
    public Proposal getProposal(String proposalId) {
        ProposalDoc doc = proposalRepository.findById(proposalId)
                .orElse(null);
        if (doc == null) return null;
        return convertToDto(doc);
    }

    /**
     * 提案を削除（物理削除）
     */
    @Transactional
    public void deleteProposal(String proposalId) {
        proposalRepository.deleteById(proposalId);
    }

    // --- Helper Methods ---

    private Proposal convertToDto(ProposalDoc doc) {
        Proposal dto = new Proposal();
        dto.set提案ID(doc.getProposalId());
        dto.set提案名(doc.getProposalName());
        dto.set募集要項(doc.getJobDescription());

        if (!doc.getCandidates().isEmpty()) {
            // 候補人材を TalentDTO に変換（全フィールド含む）
            List<TalentDTO> talents = doc.getCandidates().stream()
                    .map(candidate -> {
                        TalentDTO talent = new TalentDTO();
                        talent.setTalentId(candidate.getTalentId());
                        talent.setCompany(candidate.getCompany());
                        talent.setName(candidate.getName());
                        talent.setEmployeeNumber(candidate.getEmployeeNumber());
                        talent.setBirthDate(candidate.getBirthDate());
                        talent.setProjectEndDate(candidate.getProjectEndDate());
                        talent.setBpFlag(candidate.getBpFlag());
                        talent.setDeletedFlag(candidate.getDeletedFlag());
                        return talent;
                    })
                    .collect(Collectors.toList());
            dto.set対象人材(talents);

            // 分析結果を設定
            List<ProposalAnalyseResult> analyses = doc.getCandidates().stream()
                    .map(candidate -> {
                        ProposalAnalyseResult analysis = new ProposalAnalyseResult();
                        analysis.setTalentId(candidate.getTalentId());
                        analysis.setMatchRate(candidate.getMatchScore() != null ? candidate.getMatchScore().intValue() : 0);
                        analysis.setReason(candidate.getComment());
                        analysis.setSkills(candidate.getSkillScores().stream()
                                .map(skill -> {
                                    SkillScore s = new SkillScore();
                                    s.setSkillName(skill.getSkillName());
                                    s.setScore(skill.getScore());
                                    return s;
                                })
                                .toArray(SkillScore[]::new));
                        return analysis;
                    })
                    .collect(Collectors.toList());
            dto.set分析結果(analyses);
        }

        return dto;
    }

    private ProposalAnalyseResult findAnalysis(List<ProposalAnalyseResult> analyses, String talentId) {
        if (analyses == null) return null;
        return analyses.stream()
                .filter(a -> talentId.equals(a.getTalentId()))
                .findFirst()
                .orElse(null);
    }
}