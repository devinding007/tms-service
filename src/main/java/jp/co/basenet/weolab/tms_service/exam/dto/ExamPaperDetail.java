package jp.co.basenet.weolab.tms_service.exam.dto;

import jp.co.basenet.weolab.tms_service.exam.dto.Choice;
import jp.co.basenet.weolab.tms_service.exam.dto.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExamPaperDetail {

    private ExamPaper paper;
    private List<ExamPaperQuestionWithContent> questions = new ArrayList<>();

    public ExamPaper getPaper() {
        return paper;
    }

    public void setPaper(ExamPaper paper) {
        this.paper = paper;
    }

    public List<ExamPaperQuestionWithContent> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExamPaperQuestionWithContent> questions) {
        this.questions = questions;
    }

    public static class ExamPaper {
        private String paperId;
        private String paperName;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;

        public String getPaperId() {
            return paperId;
        }

        public void setPaperId(String paperId) {
            this.paperId = paperId;
        }

        public String getPaperName() {
            return paperName;
        }

        public void setPaperName(String paperName) {
            this.paperName = paperName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
        }
    }

    public static class ExamPaperQuestionWithContent {
        private String paperQuestionId;
        private Question question;
        private List<Choice> choices = new ArrayList<>();

        public String getPaperQuestionId() {
            return paperQuestionId;
        }

        public void setPaperQuestionId(String paperQuestionId) {
            this.paperQuestionId = paperQuestionId;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }
    }
}