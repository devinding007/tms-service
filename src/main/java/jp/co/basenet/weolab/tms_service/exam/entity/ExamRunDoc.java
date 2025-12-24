// ファイル: jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc.java

package jp.co.basenet.weolab.tms_service.exam.entity;

import jp.co.basenet.weolab.tms_service.exam.dto.SkillReflectionResult;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 試験実施ドキュメント（MongoDB 用）
 */
@Document(collection = "exam_run")
@Getter
@Setter
public class ExamRunDoc {

    /**
     * 試験実施ID（MongoDB の _id）
     */
    @Id
    @Indexed(unique = true)
    private String examRunId;

    private String talentId;
    private String participantName;
    private Boolean isRegisteredTalent = false;
    private String paperId;
    private Integer status = 0; // 0:準備中, 1:未実施, 2:実施中, 3:実施完了, 4:スキル反映済

    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private LocalDateTime reflectedAt;

    private Short totalQuestions = 0;
    private Short correctCount = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * スキル反映結果（オブジェクトとして保存）
     */
    private List<SkillReflectionResult> skillReflectionResult;
}