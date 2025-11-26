package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumeEducation {
    @JsonProperty("開始年月")
    private String startMonth;
    @JsonProperty("終了年月")
    private String endMonth;
    @JsonProperty("学校")
    private String school;
    @JsonProperty("専門")
    private String major;
    @JsonProperty("学位")
    private String degree;

    public String getStartMonth() { return startMonth; }
    public void setStartMonth(String startMonth) { this.startMonth = startMonth; }
    public String getEndMonth() { return endMonth; }
    public void setEndMonth(String endMonth) { this.endMonth = endMonth; }
    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
}
