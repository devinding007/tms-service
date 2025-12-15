package jp.co.basenet.weolab.tms_service.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 選択肢DTO
 * JSON出力時は日本語フィールド名を使用
 */
@Data
public class Choice {

  @JsonProperty("選択肢ＩＤ")
  private String choiceId;

  @JsonProperty("選択肢文章")
  private String choiceText;

  @JsonProperty("回答理由")
  private String reason;
}