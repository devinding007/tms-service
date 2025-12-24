package jp.co.basenet.weolab.tms_service.exam.dto;

public class ExamAnswerDTO {
    private String 試験用紙問題ＩＤ;
    private String 回答試験用紙選択肢ＩＤ;

    public String get試験用紙問題ＩＤ() {
        return 試験用紙問題ＩＤ;
    }

    public void set試験用紙問題ＩＤ(String 試験用紙問題ＩＤ) {
        this.試験用紙問題ＩＤ = 試験用紙問題ＩＤ;
    }

    public String get回答試験用紙選択肢ＩＤ() {
        return 回答試験用紙選択肢ＩＤ;
    }

    public void set回答試験用紙選択肢ＩＤ(String 回答試験用紙選択肢ＩＤ) {
        this.回答試験用紙選択肢ＩＤ = 回答試験用紙選択肢ＩＤ;
    }
}