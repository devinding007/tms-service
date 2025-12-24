// ファイル: jp.co.basenet.weolab.tms_service.exam.entity.ExamAnswerDoc.java

package jp.co.basenet.weolab.tms_service.exam.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 試験回答ドキュメント（MongoDB 用）
 */
@Document(collection = "exam_answer")
@Getter
@Setter
public class ExamAnswerDoc {

    @Id
    private String answerId;

    private String examRunId;
    private String paperQuestionId;
    private String selectedChoiceId;
    private String answerText;
    private LocalDateTime createdAt = LocalDateTime.now();
}