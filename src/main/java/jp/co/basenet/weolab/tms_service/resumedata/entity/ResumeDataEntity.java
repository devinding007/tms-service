package jp.co.basenet.weolab.tms_service.resumedata.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 履歴書データエンティティ（データベース用）
 *
 * 注意: このクラスはデータベースとのマッピング用なので、フィールド名は英語にする
 */
@Entity
@Table(name = "resume")
@Data
public class ResumeDataEntity {

    /**
     * 人材ID（主キー）
     * Lombokが自動生成するメソッド:
     * - getTalentId()
     * - setTalentId(String)
     */
    @Id
    @Column(name = "talent_id", nullable = false, length = 50)
    private String talentId;

    /**
     * 履歴書データ（JSON文字列）
     * Lombokが自動生成するメソッド:
     * - getResumeData()
     * - setResumeData(String)
     */
    @Column(name = "resume_data", columnDefinition = "JSON")
    private String resumeData;

    /**
     * 作成日時
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}