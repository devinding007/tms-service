// 文件路径: jp.co.basenet.weolab.tms_service.personeltalents.entity.TalentDoc.java

package jp.co.basenet.weolab.tms_service.personeltalents.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Document(collection = "talent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentDoc {

    @Id
    @Indexed
    private String talentId;          // 人材ID（String，与 JPA 一致）

    private String company;           // 所属会社
    private String name;              // 名前
    private String employeeNumber;    // 社員番号
    private LocalDate birthDate;      // 生年月日（MongoDB 支持 LocalDate）
    private LocalDate projectEndDate; // 現案件終了年月日
    private Boolean bpFlag;           // BPフラグ
    private Boolean deletedFlag;      // 削除フラグ（软删除）
}