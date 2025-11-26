package jp.co.basenet.weolab.tms_service.personel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProposalAnalyseRequest {
    
    @JsonProperty("募集要項")
    String jobPosting;
    @JsonProperty("候補要員")
    List<TalentProfile> talentInfoList;
    public String getJobPosting() {
        return jobPosting;
    }
    public void setJobPosting(String jobPosting) {
        this.jobPosting = jobPosting;
    }
    public List<TalentProfile> getTalentInfoList() {
        return talentInfoList;
    }
    public void setTalentInfoList(List<TalentProfile> talentInfoList) {
        this.talentInfoList = talentInfoList;
    }
    
    @Override
    public String toString() {
        return "TalentProfile{" +
                "募集要項=" + this.jobPosting +
                ", 候補要員=" + this.talentInfoList +
                '}';
    }
    
}
