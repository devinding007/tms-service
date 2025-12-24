// ファイル: jp.co.basenet.weolab.tms_service.proposal.entity.ProposalDoc.java

package jp.co.basenet.weolab.tms_service.proposal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 要員提案ドキュメント（MongoDB 用）
 * 候補人材の詳細情報を内嵌
 */
@Document(collection = "proposal")
@Getter
@Setter
public class ProposalDoc {

    @Id
    @Indexed(unique = true)
    private String proposalId;

    private String proposalName;
    private String jobDescription;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * 候補人材リスト（内嵌：人材の全詳細を保持）
     */
    private List<Candidate> candidates = new ArrayList<>();

    /**
     * 候補人材情報（内嵌：TalentDTO の全フィールドを保持）
     */
    @Getter
    @Setter
    public static class Candidate {
        private String talentId;
        private String company;           // 所属会社
        private String name;              // 名前
        private String employeeNumber;    // 社員番号
        private LocalDate birthDate;      // 生年月日
        private LocalDate projectEndDate; // 現案件終了年月日
        private Boolean bpFlag;           // BPフラグ
        private Boolean deletedFlag;      // 削除フラグ

        // 分析結果フィールド
        private Short matchScore;  // 0-100
        private String comment;
        private List<SkillScore> skillScores = new ArrayList<>();
        private LocalDateTime analyzedAt = LocalDateTime.now();
    }

    @Getter
    @Setter
    public static class SkillScore {
        private String skillName;
        private Integer score;
    }
}