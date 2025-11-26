package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * "スキル採点情報" ブロック。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillScoreInfo {

    @JsonProperty("人材ＩＤ")
    private String talentId;

    @JsonProperty("スキル")
    private List<SkillScore> skills;

    public SkillScoreInfo() {
    }

    public String getTalentId() {
        return talentId;
    }

    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }

    public List<SkillScore> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillScore> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "SkillScoreInfo{" +
                "talentId='" + talentId + '\'' +
                ", skills=" + skills +
                '}';
    }
}
