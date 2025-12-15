package jp.co.basenet.weolab.tms_service.skill.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "talent_skill_score", indexes = {
        @Index(name = "idx_talent_id", columnList = "talent_id")
})
@IdClass(TalentSkillScoreId.class) // ← 追加
@Data
public class TalentSkillScore {

    @Id
    @Column(name = "talent_id", nullable = false, length = 50)
    private String talentId;

    @Id
    @Column(name = "skill_name", nullable = false, length = 100)
    private String skillName;

    @Column(name = "skill_score", nullable = false)
    private Integer skillScore;
}