package jp.co.basenet.weolab.tms_service.personel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResumeWorkHistory {
    @JsonProperty("期間開始")
    private String periodStart;
    @JsonProperty("期間終了")
    private String periodEnd;
    @JsonProperty("役割")
    private String role;
    @JsonProperty("チーム規模")
    private String teamSize;
    @JsonProperty("利用技術")
    private List<String> technologies;
    @JsonProperty("担当業務詳細")
    private String detail;

    public String getPeriodStart() { return periodStart; }
    public void setPeriodStart(String periodStart) { this.periodStart = periodStart; }
    public String getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(String periodEnd) { this.periodEnd = periodEnd; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getTeamSize() { return teamSize; }
    public void setTeamSize(String teamSize) { this.teamSize = teamSize; }
    public List<String> getTechnologies() { return technologies; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
}
