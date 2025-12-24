package jp.co.basenet.weolab.tms_service.exam.dto;

import java.util.List;

public class ExamSubmissionRequest {
    private String 試験ＩＤ;
    private List<ExamAnswerDTO> 試験問題解答;

    public String get試験ＩＤ() {
        return 試験ＩＤ;
    }

    public void set試験ＩＤ(String 試験ＩＤ) {
        this.試験ＩＤ = 試験ＩＤ;
    }

    public List<ExamAnswerDTO> get試験問題解答() {
        return 試験問題解答;
    }

    public void set試験問題解答(List<ExamAnswerDTO> 試験問題解答) {
        this.試験問題解答 = 試験問題解答;
    }
}