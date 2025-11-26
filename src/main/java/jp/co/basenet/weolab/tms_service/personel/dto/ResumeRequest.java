package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResumeRequest {

    @NotBlank
    @JsonProperty("氏名")
    private String name;

    @JsonProperty("出生年月")
    private String birthMonth;

    @JsonProperty("国籍")
    private String nationality;

    @JsonProperty("来日年月")
    private String arrivalInJapan;

    @JsonProperty("最寄駅")
    private String nearestStation;

    @JsonProperty("アピールポイント")
    private String highlights;

    @JsonProperty("言語")
    private List<ResumeLanguageSkill> languages;

    @JsonProperty("学歴")
    private List<ResumeEducation> educations;

    @JsonProperty("スキル")
    private List<ResumeSkill> skills;

    @JsonProperty("資格")
    private List<String> qualifications;

    @JsonProperty("業務歴")
    private List<ResumeWorkHistory> workHistory;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBirthMonth() { return birthMonth; }
    public void setBirthMonth(String birthMonth) { this.birthMonth = birthMonth; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getArrivalInJapan() { return arrivalInJapan; }
    public void setArrivalInJapan(String arrivalInJapan) { this.arrivalInJapan = arrivalInJapan; }

    public String getNearestStation() { return nearestStation; }
    public void setNearestStation(String nearestStation) { this.nearestStation = nearestStation; }

    public String getHighlights() { return highlights; }
    public void setHighlights(String highlights) { this.highlights = highlights; }

    public List<ResumeLanguageSkill> getLanguages() { return languages; }
    public void setLanguages(List<ResumeLanguageSkill> languages) { this.languages = languages; }

    public List<ResumeEducation> getEducations() { return educations; }
    public void setEducations(List<ResumeEducation> educations) { this.educations = educations; }

    public List<ResumeSkill> getSkills() { return skills; }
    public void setSkills(List<ResumeSkill> skills) { this.skills = skills; }

    public List<String> getQualifications() { return qualifications; }
    public void setQualifications(List<String> qualifications) { this.qualifications = qualifications; }

    public List<ResumeWorkHistory> getWorkHistory() { return workHistory; }
    public void setWorkHistory(List<ResumeWorkHistory> workHistory) { this.workHistory = workHistory; }
}
