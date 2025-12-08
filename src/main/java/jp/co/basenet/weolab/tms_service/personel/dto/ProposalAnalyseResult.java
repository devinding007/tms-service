package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProposalAnalyseResult {
    @JsonProperty("人材ＩＤ")
    private String talentId;
    @JsonProperty("マッチ率")
    private Integer matchRate;
    @JsonProperty("理由")
    private String reason;
    @JsonProperty("スキル採点")
    private SkillScore[] skills;

    public SkillScore[] getSkills() {
        return skills;
    }
    public void setSkills(SkillScore[] skills) {
        this.skills = skills;
    }
    public String getTalentId() {
        return talentId;
    }
    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }
    public Integer getMatchRate() {
        return matchRate;
    }
    public void setMatchRate(Integer matchRate) {
        this.matchRate = matchRate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
