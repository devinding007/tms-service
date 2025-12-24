package jp.co.basenet.weolab.tms_service.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 試験実施詳細画面用の試験用紙DTO（問題リストを含む）
 * 既存の ExamPaperResponse と完全分離し、試験用紙管理モジュールに影響を与えない
 */
@Data
public class ExamPaperDetailResponse {
    @JsonProperty("試験用紙ID")
    private String 試験用紙ＩＤ;

    @JsonProperty("試験用紙名称")
    private String 試験用紙名称;

    @JsonProperty("説明")
    private String 説明;

    @JsonProperty("作成日時")
    private LocalDateTime 作成日時;

    private LocalDateTime deletedAt;

    // 增：問題リスト
    private List<ExamPaperQuestion> 問題リスト;
}