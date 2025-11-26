package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * スキル採点情報.スキル
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillScore {

    @JsonProperty("スキル名")
    private String skillName;

    @JsonProperty("スキル点数")
    private Integer score;

    public SkillScore() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SkillScore{" +
                "skillName='" + skillName + '\'' +
                ", score=" + score +
                '}';
    }
}
