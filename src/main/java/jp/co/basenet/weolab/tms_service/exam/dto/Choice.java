package jp.co.basenet.weolab.tms_service.exam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Choice {

  @JsonProperty("選択肢ＩＤ")
  String choiceId;

  @JsonProperty("選択肢文章")
  String choiceText;

  @JsonProperty("回答理由")
  String reason;
}
