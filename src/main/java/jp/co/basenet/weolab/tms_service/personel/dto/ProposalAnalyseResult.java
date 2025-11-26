package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProposalAnalyseResult {
    @JsonProperty("人材ＩＤ")
    private String talentId;
    @JsonProperty("マッチ率")
    private Integer matchRate;
    @JsonProperty("理由")
    private String reason;
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
