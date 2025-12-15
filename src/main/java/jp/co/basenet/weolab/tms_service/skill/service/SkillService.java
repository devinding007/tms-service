package jp.co.basenet.weolab.tms_service.skill.service;

import jp.co.basenet.weolab.tms_service.skill.dto.PersonnelSkillPayload;
import jp.co.basenet.weolab.tms_service.skill.entity.TalentSkillScore;
import jp.co.basenet.weolab.tms_service.skill.repository.TalentSkillScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * スキルスコアのサービス
 */
@Service
@RequiredArgsConstructor
public class SkillService {

    private final TalentSkillScoreRepository skillScoreRepository;

    /**
     * 指定された人材IDのスキルデータを取得する
     */
    public PersonnelSkillPayload getSkillPayloadByTalentId(String talentId) {
        List<TalentSkillScore> scores = skillScoreRepository.findByTalentId(talentId);
        if (scores.isEmpty()) {
            return null;
        }

        PersonnelSkillPayload payload = new PersonnelSkillPayload();
        payload.set人材ＩＤ(talentId);
        payload.setスキル(scores.stream()
                .map(score -> {
                    PersonnelSkillPayload.SkillItem item = new PersonnelSkillPayload.SkillItem();
                    item.setスキル名(score.getSkillName());
                    item.setスキル点数(score.getSkillScore());
                    return item;
                })
                .collect(Collectors.toList()));
        return payload;
    }

    /**
     * スキルデータを全量上書き保存する
     */
    @Transactional
    public void saveSkillPayload(PersonnelSkillPayload payload) {
        String talentId = payload.get人材ＩＤ();
        // 1. 既存データを削除
        skillScoreRepository.deleteByTalentId(talentId);

        // 2. 新データを挿入
        List<TalentSkillScore> entities = payload.getスキル().stream()
                .map(item -> {
                    TalentSkillScore entity = new TalentSkillScore();
                    entity.setTalentId(talentId);
                    entity.setSkillName(item.getスキル名());
                    entity.setSkillScore(item.getスキル点数());
                    return entity;
                })
                .collect(Collectors.toList());

        skillScoreRepository.saveAll(entities);
    }


    public List<String> getAllSkillNames() {
        return skillScoreRepository.findAllSkillNames();
    }


}