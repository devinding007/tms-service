package jp.co.basenet.weolab.tms_service.personel.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResumeAnalysis {
    @JsonProperty("一言集約") private String summary;
    @JsonProperty("強み領域") private java.util.List<ResumeStrengthArea> strengths;
    @JsonProperty("一番得意な領域") private String topDomain;
    @JsonProperty("アサイン適正") private java.util.List<String> assignments;
    @JsonProperty("伸ばすと効く") private java.util.List<ResumeGrowthArea> growth;
    @JsonProperty("スキル採点") private java.util.List<ResumeSkillScore> skillScores;
    public String getSummary(){return summary;} public void setSummary(String v){summary=v;}
    public java.util.List<ResumeStrengthArea> getStrengths(){return strengths;} public void setStrengths(java.util.List<ResumeStrengthArea> v){strengths=v;}
    public String getTopDomain(){return topDomain;} public void setTopDomain(String v){topDomain=v;}
    public java.util.List<String> getAssignments(){return assignments;} public void setAssignments(java.util.List<String> v){assignments=v;}
    public java.util.List<ResumeGrowthArea> getGrowth(){return growth;} public void setGrowth(java.util.List<ResumeGrowthArea> v){growth=v;}
    public java.util.List<ResumeSkillScore> getSkillScores(){return skillScores;} public void setSkillScores(java.util.List<ResumeSkillScore> v){skillScores=v;}
}
