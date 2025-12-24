// ExamPaperResponse.java
package jp.co.basenet.weolab.tms_service.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamPaperResponse {
    @JsonProperty("試験用紙ID")
    private String paperId;

    @JsonProperty("試験用紙名称")
    private String paperName;

    @JsonProperty("説明")
    private String description;

    @JsonProperty("作成日時")
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt; // 削除フラグは日本語不要
}