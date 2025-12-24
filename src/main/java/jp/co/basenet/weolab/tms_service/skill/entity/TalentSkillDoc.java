// ファイルパス: jp.co.basenet.weolab.tms_service.skill.entity.TalentSkillDoc.java

package jp.co.basenet.weolab.tms_service.skill.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 人材のスキル情報ドキュメント（MongoDB 用）
 * 1人材 = 1ドキュメント、スキルは配列で保持
 */
@Document(collection = "talent_skill")
@Data
public class TalentSkillDoc {

    /**
     * ドキュメントID（MongoDB の _id にマッピング）
     * 通常、talentId と同じ値を設定
     */
    @Id
    private String id;

    /**
     * 人材ID（talentId）
     */
    @Indexed(unique = true) // 人材IDで一意性を保証
    private String talentId;

    /**
     * スキル一覧
     */
    private List<SkillItem> skills;

    /**
     * 作成日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /**
     * スキルアイテム（内部クラス）
     */
    @Data
    public static class SkillItem {
        private String skillName;
        private Integer skillScore;
    }
}