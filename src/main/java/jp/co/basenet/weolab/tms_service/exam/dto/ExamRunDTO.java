package jp.co.basenet.weolab.tms_service.exam.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExamRunDTO {
    private String 試験ＩＤ;
    private String 参加者人材ＩＤ;
    private String 参加者氏名;
    private Integer 登録済人材;
    private Integer 試験ステータス;
    private String 試験用紙ＩＤ;
    private ExamPaperDetailResponse 試験用紙;
    private LocalDateTime 試験実施日時;
    private LocalDateTime 試験提出日時;
    private Short 試験問題数;
    private Short 試験正解数;
    private List<ExamAnswerDTO> 試験問題解答;
    private List<SkillReflectionResult> スキル反映結果;

    // Getters and Setters
    public String get試験ＩＤ() {
        return 試験ＩＤ;
    }

    public void set試験ＩＤ(String 試験ＩＤ) {
        this.試験ＩＤ = 試験ＩＤ;
    }

    public String get参加者人材ＩＤ() {
        return 参加者人材ＩＤ;
    }

    public void set参加者人材ＩＤ(String 参加者人材ＩＤ) {
        this.参加者人材ＩＤ = 参加者人材ＩＤ;
    }

    public String get参加者氏名() {
        return 参加者氏名;
    }

    public void set参加者氏名(String 参加者氏名) {
        this.参加者氏名 = 参加者氏名;
    }

    public Integer get登録済人材() {
        return 登録済人材;
    }

    public void set登録済人材(Integer 登録済人材) {
        this.登録済人材 = 登録済人材;
    }

    public Integer get試験ステータス() {
        return 試験ステータス;
    }

    public void set試験ステータス(Integer 試験ステータス) {
        this.試験ステータス = 試験ステータス;
    }

    public String get試験用紙ＩＤ() {
        return 試験用紙ＩＤ;
    }

    public void set試験用紙ＩＤ(String 試験用紙ＩＤ) {
        this.試験用紙ＩＤ = 試験用紙ＩＤ;
    }



    public void set試験用紙(ExamPaperDetailResponse 試験用紙) {
        this.試験用紙 = 試験用紙;
    }

    public ExamPaperDetailResponse get試験用紙() {
        return 試験用紙;
    }
    public LocalDateTime get試験実施日時() {
        return 試験実施日時;
    }

    public void set試験実施日時(LocalDateTime 試験実施日時) {
        this.試験実施日時 = 試験実施日時;
    }

    public LocalDateTime get試験提出日時() {
        return 試験提出日時;
    }

    public void set試験提出日時(LocalDateTime 試験提出日時) {
        this.試験提出日時 = 試験提出日時;
    }

    public Short get試験問題数() {
        return 試験問題数;
    }

    public void set試験問題数(Short 試験問題数) {
        this.試験問題数 = 試験問題数;
    }

    public Short get試験正解数() {
        return 試験正解数;
    }

    public void set試験正解数(Short 試験正解数) {
        this.試験正解数 = 試験正解数;
    }

    public List<ExamAnswerDTO> get試験問題解答() {
        return 試験問題解答;
    }

    public void set試験問題解答(List<ExamAnswerDTO> 試験問題解答) {
        this.試験問題解答 = 試験問題解答;
    }

    public List<SkillReflectionResult> getスキル反映結果() {
        return スキル反映結果;
    }

    public void setスキル反映結果(List<SkillReflectionResult> スキル反映結果) {
        this.スキル反映結果 = スキル反映結果;
    }
}