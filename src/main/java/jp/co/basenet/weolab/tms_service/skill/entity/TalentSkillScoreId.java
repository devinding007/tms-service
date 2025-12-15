package jp.co.basenet.weolab.tms_service.skill.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * TalentSkillScore の複合主キー用クラス
 */
public class TalentSkillScoreId implements Serializable {

    private String talentId;
    private String skillName;

    // 必須：引数なしコンストラクタ
    public TalentSkillScoreId() {}

    public TalentSkillScoreId(String talentId, String skillName) {
        this.talentId = talentId;
        this.skillName = skillName;
    }

    // equals / hashCode は必須
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalentSkillScoreId that = (TalentSkillScoreId) o;
        return Objects.equals(talentId, that.talentId) &&
                Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(talentId, skillName);
    }
}