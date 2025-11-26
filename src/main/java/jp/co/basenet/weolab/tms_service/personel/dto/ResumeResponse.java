package jp.co.basenet.weolab.tms_service.personel.dto;

import java.time.Instant;

public class ResumeResponse {
    private String status;
    private String resumeId;
    private Instant receivedAt;
    private String primaryName;
    private int languageCount;
    private int skillCount;

    public ResumeResponse() {}

    public ResumeResponse(String status, String resumeId, Instant receivedAt, String primaryName, int languageCount, int skillCount) {
        this.status = status;
        this.resumeId = resumeId;
        this.receivedAt = receivedAt;
        this.primaryName = primaryName;
        this.languageCount = languageCount;
        this.skillCount = skillCount;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getResumeId() { return resumeId; }
    public void setResumeId(String resumeId) { this.resumeId = resumeId; }
    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }
    public String getPrimaryName() { return primaryName; }
    public void setPrimaryName(String primaryName) { this.primaryName = primaryName; }
    public int getLanguageCount() { return languageCount; }
    public void setLanguageCount(int languageCount) { this.languageCount = languageCount; }
    public int getSkillCount() { return skillCount; }
    public void setSkillCount(int skillCount) { this.skillCount = skillCount; }
}
