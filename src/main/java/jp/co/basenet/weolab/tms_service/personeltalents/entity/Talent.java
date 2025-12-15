package jp.co.basenet.weolab.tms_service.personeltalents.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "talent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Talent {

    @Id
    private String talentId;          // 人材ID

    private String company;           // 所属会社

    private String name;              // 名前

    private String employeeNumber;    // 社員番号

    private LocalDate birthDate;      // 生年月日

    private LocalDate projectEndDate; // 現案件終了年月日

    private Boolean bpFlag;           // BPフラグ

    private Boolean deletedFlag;      // 削除フラグ
}
