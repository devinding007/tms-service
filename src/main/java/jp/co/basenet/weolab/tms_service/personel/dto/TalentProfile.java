package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 配列の各要素に相当するルートオブジェクト。
 * { "人材情報": {...}, "経歴情報": {...}, "スキル採点情報": {...} }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TalentProfile {

    @JsonProperty("id")
    private String id;

    @JsonProperty("経歴情報")
    private ResumeRequest resumeRequest;

    @JsonProperty("スキル採点情報")
    private SkillScoreInfo skillScoreInfo;

    public TalentProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResumeRequest getResumeRequest() {
        return resumeRequest;
    }

    public void setResumeRequest(ResumeRequest resumeRequest) {
        this.resumeRequest = resumeRequest;
    }

    public SkillScoreInfo getSkillScoreInfo() {
        return skillScoreInfo;
    }

    public void setSkillScoreInfo(SkillScoreInfo skillScoreInfo) {
        this.skillScoreInfo = skillScoreInfo;
    }

    @Override
    public String toString() {
        return "TalentProfile{" +
                "basicInfo=" + this.id +
                ", careerDetail=" + this.resumeRequest +
                ", skillScoreInfo=" + this.skillScoreInfo +
                '}';
    }

}
