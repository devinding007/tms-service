package jp.co.basenet.weolab.tms_service.personeltalents.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentDTO {

    @JsonProperty("talentId")      // 序列化用英文（兼容旧前端）
    @JsonAlias("人材ＩＤ")
    private String talentId;

    @JsonProperty("company")
    @JsonAlias("所属会社")
    private String company;

    @JsonProperty("name")
    @JsonAlias("名前")
    private String name;

    @JsonProperty("employeeNumber")
    @JsonAlias("社員番号")
    private String employeeNumber;

    @JsonProperty("birthDate")
    @JsonAlias("生年月日")
    private LocalDate birthDate;

    @JsonProperty("projectEndDate")
    @JsonAlias("現案件終了年月日")
    private LocalDate projectEndDate;

    @JsonProperty("bpFlag")
    @JsonAlias("BPフラグ")
    private Boolean bpFlag;

    private Boolean deletedFlag;
}
