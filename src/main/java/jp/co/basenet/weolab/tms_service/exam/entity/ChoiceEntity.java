package jp.co.basenet.weolab.tms_service.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 選択肢エンティティ（choiceテーブル対応）
 */
@Entity
@Table(name = "choice")
@Getter
@Setter
public class ChoiceEntity {

    @Id
    @Column(name = "choice_id", length = 36, nullable = false)
    private String choiceId;

    @Column(name = "question_id", nullable = false, length = 36)
    private String questionId;

    @Column(name = "choice_text", nullable = false, columnDefinition = "TEXT")
    private String choiceText;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}