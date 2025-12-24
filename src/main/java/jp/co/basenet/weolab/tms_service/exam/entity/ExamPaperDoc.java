// ファイル: jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc.java

package jp.co.basenet.weolab.tms_service.exam.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 試験用紙ドキュメント（MongoDB 用）
 */
@Document(collection = "exam_paper")
@Getter
@Setter
public class ExamPaperDoc {

    @Id
    @Indexed(unique = true)
    private String paperId;

    private String paperName;
    private String description;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 試験用紙に含まれる問題参照リスト
     */
    private List<QuestionRef> questions = new ArrayList<>();

    /**
     * 問題参照情報（内嵌）
     */
    @Getter
    @Setter
    public static class QuestionRef {
        private String paperQuestionId;
        private String questionId;          // QuestionDoc の ID
        private Integer displayOrder;
        private String skill;
        private Integer difficulty;
        private String modelAnswerChoiceId;
    }
}