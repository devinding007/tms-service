// ファイル: jp.co.basenet.weolab.tms_service.exam.service.ExamRunService.java

package jp.co.basenet.weolab.tms_service.exam.service;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc;
import jp.co.basenet.weolab.tms_service.exam.entity.ExamAnswerDoc;
import jp.co.basenet.weolab.tms_service.exam.repository.ExamRunRepository;
import jp.co.basenet.weolab.tms_service.exam.repository.ExamAnswerRepository;
import jp.co.basenet.weolab.tms_service.exam.dto.*;
import jp.co.basenet.weolab.tms_service.skill.service.SkillService;
import jp.co.basenet.weolab.tms_service.skill.dto.PersonnelSkillPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 試験実施サービス（MongoDB 版）
 * 全機能を JPA 版と完全に一致させる
 */
@Service
@Transactional
public class ExamRunService {

    private final ExamRunRepository examRunRepository;
    private final ExamAnswerRepository examAnswerRepository;
    private final SkillService skillService;
    private final ExamPaperService examPaperService;

    public ExamRunService(
            ExamRunRepository examRunRepository,
            ExamAnswerRepository examAnswerRepository,
            SkillService skillService,
            ExamPaperService examPaperService
    ) {
        this.examRunRepository = examRunRepository;
        this.examAnswerRepository = examAnswerRepository;
        this.skillService = skillService;
        this.examPaperService = examPaperService;
    }

    @Transactional
    public ExamRunDTO createExamRun(ExamRunDTO dto) {
        ExamRunDoc doc = new ExamRunDoc();
        String examRunId = UUID.randomUUID().toString();
        doc.setExamRunId(examRunId);
        doc.setTalentId(dto.get参加者人材ＩＤ());
        doc.setParticipantName(dto.get参加者氏名());
        doc.setIsRegisteredTalent(dto.get登録済人材() != null && dto.get登録済人材() == 1);

        if (dto.get試験用紙() != null) {
            doc.setPaperId(dto.get試験用紙().get試験用紙ＩＤ());
        } else {
            throw new IllegalArgumentException("試験用紙が指定されていません");
        }
        doc.setStatus(0);
        doc.setCreatedAt(LocalDateTime.now());
        doc.setUpdatedAt(LocalDateTime.now());

        ExamRunDoc saved = examRunRepository.save(doc);
        return convertToDTO(saved, false);
    }

    @Transactional(readOnly = true)
    public ExamRunDTO getExamRun(String id) {
        ExamRunDoc doc = examRunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("試験実施が見つかりません: " + id));
        return convertToDTO(doc, true);
    }

    @Transactional
    public ExamRunDTO confirmExamRun(String examRunId) {
        ExamRunDoc doc = examRunRepository.findById(examRunId)
                .orElseThrow(() -> new RuntimeException("試験実施が見つかりません"));
        if (!Objects.equals(doc.getStatus(), 0)) {
            throw new RuntimeException("試験ステータスが「準備中」ではありません");
        }
        doc.setStatus(1);
        doc.setUpdatedAt(LocalDateTime.now());
        ExamRunDoc saved = examRunRepository.save(doc);
        return convertToDTO(saved, false);
    }

    @Transactional
    public ExamRunDTO startExamRun(String examRunId) {
        ExamRunDoc doc = examRunRepository.findById(examRunId)
                .orElseThrow(() -> new RuntimeException("試験実施が見つかりません"));
        if (!Objects.equals(doc.getStatus(), 1)) {
            throw new RuntimeException("試験ステータスが「未実施」ではありません");
        }
        doc.setStatus(2);
        doc.setStartedAt(LocalDateTime.now());
        doc.setUpdatedAt(LocalDateTime.now());
        ExamRunDoc saved = examRunRepository.save(doc);
        return convertToDTO(saved, false);
    }

    @Transactional
    public void submitExamAnswers(ExamSubmissionRequest request) {
        ExamRunDoc examRun = examRunRepository.findById(request.get試験ＩＤ())
                .orElseThrow(() -> new RuntimeException("試験実施が見つかりません"));

        if (!Objects.equals(examRun.getStatus(), 2)) {
            throw new RuntimeException("試験ステータスが「実施中」ではありません");
        }

        examAnswerRepository.deleteByExamRunId(examRun.getExamRunId());

        List<ExamAnswerDoc> answers = request.get試験問題解答().stream()
                .map(dto -> {
                    ExamAnswerDoc ans = new ExamAnswerDoc();
                    ans.setAnswerId(UUID.randomUUID().toString());
                    ans.setExamRunId(examRun.getExamRunId());
                    ans.setPaperQuestionId(dto.get試験用紙問題ＩＤ());
                    ans.setSelectedChoiceId(dto.get回答試験用紙選択肢ＩＤ());
                    return ans;
                })
                .collect(Collectors.toList());

        examAnswerRepository.saveAll(answers);

        ExamPaperDetail paperDetail = examPaperService.getPaper(examRun.getPaperId());
        if (paperDetail == null) {
            throw new RuntimeException("試験用紙が見つかりません");
        }

        Map<String, String> modelAnswerMap = paperDetail.getQuestions().stream()
                .collect(Collectors.toMap(
                        q -> q.getPaperQuestionId(),
                        q -> q.getQuestion().getModelAnswerChoiceId()
                ));

        short correctCount = 0;
        for (ExamAnswerDoc ans : answers) {
            String modelAnswer = modelAnswerMap.get(ans.getPaperQuestionId());
            if (modelAnswer != null && modelAnswer.equals(ans.getSelectedChoiceId())) {
                correctCount++;
            }
        }

        examRun.setTotalQuestions((short) paperDetail.getQuestions().size());
        examRun.setCorrectCount(correctCount);
        examRun.setSubmittedAt(LocalDateTime.now());
        examRun.setStatus(3);
        examRun.setUpdatedAt(LocalDateTime.now());
        examRunRepository.save(examRun);
    }

    @Transactional
    public List<SkillReflectionResult> reflectSkillPoint(String examRunId) {
        ExamRunDoc examRun = examRunRepository.findById(examRunId)
                .orElseThrow(() -> new RuntimeException("試験実施が見つかりません"));

        if (!Objects.equals(examRun.getStatus(), 3)) {
            throw new RuntimeException("試験ステータスが「実施完了」ではありません");
        }
        if (examRun.getTalentId() == null) {
            throw new RuntimeException("登録済人材でないため、スキル反映できません");
        }

        String talentId = examRun.getTalentId();
        PersonnelSkillPayload currentPayload = skillService.getSkillPayloadByPersonnelId(talentId);
        Map<String, Integer> currentSkillMap = new HashMap<>();
        if (currentPayload != null) {
            for (PersonnelSkillPayload.SkillItem item : currentPayload.getスキル()) {
                currentSkillMap.put(item.getスキル名(), item.getスキル点数());
            }
        }

        ExamPaperDetail paperDetail = examPaperService.getPaper(examRun.getPaperId());
        List<ExamAnswerDoc> answers = examAnswerRepository.findByExamRunId(examRunId);
        Map<String, String> answerMap = answers.stream()
                .collect(Collectors.toMap(ExamAnswerDoc::getPaperQuestionId, ExamAnswerDoc::getSelectedChoiceId));

        Map<String, Integer> newSkillMap = new HashMap<>(currentSkillMap);
        for (var q : paperDetail.getQuestions()) {
            String skill = q.getQuestion().getSkill();
            Integer oldPoint = newSkillMap.getOrDefault(skill, 0);
            String modelAnswer = q.getQuestion().getModelAnswerChoiceId();
            String userAnswer = answerMap.get(q.getPaperQuestionId());
            boolean isCorrect = modelAnswer != null && modelAnswer.equals(userAnswer);
            int newPoint = calcNewSkillPoint(oldPoint, q.getQuestion().getDifficulty(), isCorrect);
            newSkillMap.put(skill, newPoint);
        }

        PersonnelSkillPayload newPayload = new PersonnelSkillPayload();
        newPayload.set人材ＩＤ(talentId);
        newPayload.setスキル(newSkillMap.entrySet().stream()
                .map(entry -> {
                    var item = new PersonnelSkillPayload.SkillItem();
                    item.setスキル名(entry.getKey());
                    item.setスキル点数(entry.getValue());
                    return item;
                })
                .collect(Collectors.toList()));
        skillService.saveSkillPayload(newPayload);

        List<SkillReflectionResult> results = newSkillMap.entrySet().stream()
                .filter(entry -> !Objects.equals(entry.getValue(), currentSkillMap.get(entry.getKey())))
                .map(entry -> {
                    SkillReflectionResult res = new SkillReflectionResult();
                    res.setスキル名(entry.getKey());
                    res.setスキル点数更新前(currentSkillMap.getOrDefault(entry.getKey(), 0));
                    res.setスキル点数更新後(entry.getValue());
                    return res;
                })
                .collect(Collectors.toList());

        examRun.setSkillReflectionResult(results);
        examRun.setReflectedAt(LocalDateTime.now());
        examRun.setStatus(4);
        examRun.setUpdatedAt(LocalDateTime.now());
        examRunRepository.save(examRun);

        return results;
    }

    private int calcNewSkillPoint(int oldPoint, int level, boolean isRightAnswer) {
        double alpha = oldPoint;
        double beta = 100 - oldPoint;
        int infoAmount = level;
        if (isRightAnswer) {
            alpha += infoAmount;
        } else {
            beta += (11 - infoAmount);
        }
        return (int) Math.ceil((alpha / (alpha + beta)) * 100);
    }

    private ExamRunDTO convertToDTO(ExamRunDoc doc, boolean includePaper) {
        ExamRunDTO dto = new ExamRunDTO();
        dto.set試験ＩＤ(doc.getExamRunId());
        dto.set参加者人材ＩＤ(doc.getTalentId());
        dto.set参加者氏名(doc.getParticipantName());
        dto.set登録済人材(doc.getIsRegisteredTalent() ? 1 : 0);
        dto.set試験ステータス(doc.getStatus());
        dto.set試験用紙ＩＤ(doc.getPaperId());
        dto.set試験実施日時(doc.getStartedAt());
        dto.set試験提出日時(doc.getSubmittedAt());
        dto.set試験問題数(doc.getTotalQuestions());
        dto.set試験正解数(doc.getCorrectCount());
        dto.setスキル反映結果(doc.getSkillReflectionResult() != null ? doc.getSkillReflectionResult() : Collections.emptyList());

        if (includePaper && doc.getPaperId() != null) {
            try {
                ExamPaperDetailResponse paperResponse = examPaperService.getExamPaperById(doc.getPaperId());
                dto.set試験用紙(paperResponse);
            } catch (Exception e) {
                // 試験用紙が取得できなくても継続
            }
        }
        return dto;
    }
}