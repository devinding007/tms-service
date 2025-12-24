// src/main/java/.../exam/dto/ExamPaperRequest.java
package jp.co.basenet.weolab.tms_service.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamPaperRequest {

    @JsonProperty("paper_id")  // ← これを追加
    private String paperId;

    private Paper paper;

    private List<ExamPaperQuestionRef> questionRefs = new ArrayList<>();
    private List<Question> newQuestions = new ArrayList<>();

    @Data
    public static class Paper {
        @JsonProperty("paper_name")  // ← これを追加
        private String paperName;

        @JsonProperty("description") // ← これも追加（安全のため）
        private String description;
    }

    @Data
    public static class ExamPaperQuestionRef {
        @JsonProperty("paper_question_id")
        private String paperQuestionId;

        @JsonProperty("question_id")
        private String questionId;

        @JsonProperty("display_order")
        private Integer displayOrder;
    }
}