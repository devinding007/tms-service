package jp.co.basenet.weolab.tms_service.personeltalents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentDTO {

    private String talentId;
    private String company;
    private String name;
    private String employeeNumber;
    private LocalDate birthDate;
    private LocalDate projectEndDate;
    private Boolean bpFlag;
    private Boolean deletedFlag;
}
