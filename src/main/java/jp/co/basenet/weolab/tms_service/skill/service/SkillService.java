// ファイルパス: jp.co.basenet.weolab.tms_service.skill.service.SkillService.java

package jp.co.basenet.weolab.tms_service.skill.service;

import jp.co.basenet.weolab.tms_service.skill.dto.PersonnelSkillPayload;
import jp.co.basenet.weolab.tms_service.skill.entity.TalentSkillDoc;
import jp.co.basenet.weolab.tms_service.skill.repository.TalentSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * スキルスコアのサービス（MongoDB 版）
 */
@Service
@RequiredArgsConstructor
public class SkillService {

    private final TalentSkillRepository skillRepository;

    /**
     * 指定された人材IDのスキルデータを取得します。
     *
     * @param talentId 人材ID
     * @return スキルデータ（存在しない場合は null）
     */
    public PersonnelSkillPayload getSkillPayloadByTalentId(String talentId) {
        return skillRepository.findById(talentId)
                .map(doc -> {
                    PersonnelSkillPayload payload = new PersonnelSkillPayload();
                    payload.set人材ＩＤ(doc.getTalentId());
                    payload.setスキル(doc.getSkills().stream()
                            .map(item -> {
                                PersonnelSkillPayload.SkillItem dtoItem = new PersonnelSkillPayload.SkillItem();
                                dtoItem.setスキル名(item.getSkillName());
                                dtoItem.setスキル点数(item.getSkillScore());
                                return dtoItem;
                            })
                            .collect(Collectors.toList()));
                    return payload;
                })
                .orElse(null);
    }

    /**
     * スキルデータを保存（更新）します。
     * 既存のスキルは上書きされ、新しいスキルは追加されます。
     *
     * @param payload 保存するスキル情報
     */
    public void saveSkillPayload(PersonnelSkillPayload payload) {
        String talentId = payload.get人材ＩＤ();
        if (talentId == null) {
            throw new IllegalArgumentException("人材IDが指定されていません");
        }

        TalentSkillDoc doc = new TalentSkillDoc();
        doc.setId(talentId);
        doc.setTalentId(talentId);
        doc.setSkills(payload.getスキル().stream()
                .map(dtoItem -> {
                    TalentSkillDoc.SkillItem item = new TalentSkillDoc.SkillItem();
                    item.setSkillName(dtoItem.getスキル名());
                    item.setSkillScore(dtoItem.getスキル点数());
                    return item;
                })
                .collect(Collectors.toList()));

        LocalDateTime now = LocalDateTime.now();
        // 既存ドキュメントがあれば createdAt を維持
        skillRepository.findById(talentId)
                .ifPresentOrElse(
                        existing -> doc.setCreatedAt(existing.getCreatedAt()),
                        () -> doc.setCreatedAt(now)
                );
        doc.setUpdatedAt(now);

        skillRepository.save(doc);
    }

    // 後方互換性のためのエイリアス
    public PersonnelSkillPayload getSkillPayloadByPersonnelId(String talentId) {
        return getSkillPayloadByTalentId(talentId);
    }

    /**
     * 登録済みの全スキル名を取得します。
     * MongoDB の Aggregation を使用して、全スキル名を重複なしで取得します。
     */
    public List<String> getAllSkillNames() {
        List<Map<String, Object>> result = skillRepository.aggregateAllSkillNames();
        if (result.isEmpty()) {
            return List.of(); // 空リストを返す（Java 9+）
        }

        // 最初の結果から skillNames を取得
        Map<String, Object> first = result.get(0);
        Object skillNamesObj = first.get("skillNames");

        if (skillNamesObj instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<String> skillNames = (List<String>) skillNamesObj;
            return skillNames;
        }

        return List.of();
    }
}