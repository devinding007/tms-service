package jp.co.basenet.weolab.tms_service.skill.dto;

import lombok.Data;

import java.util.List;

/**
 * フロントエンドと連携するスキルペイロードDTO（全角フィールド名に統一）
 */
@Data
public class PersonnelSkillPayload {

    /**
     * 人材ID
     */
    private String 人材ＩＤ;

    /**
     * スキルリスト
     */
    private List<SkillItem> スキル;

    /**
     * スキル項目
     */
    @Data
    public static class SkillItem {
        private String スキル名;
        private Integer スキル点数;
    }
}