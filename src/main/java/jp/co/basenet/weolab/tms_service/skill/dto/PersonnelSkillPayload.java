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

        public String getスキル名() { return スキル名; }
        public void setスキル名(String スキル名) { this.スキル名 = スキル名; }

        public Integer getスキル点数() { return スキル点数; }
        public void setスキル点数(Integer スキル点数) { this.スキル点数 = スキル点数; }
    }
}