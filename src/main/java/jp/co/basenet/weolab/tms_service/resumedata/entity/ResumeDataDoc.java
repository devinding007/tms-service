// ファイルパス: jp.co.basenet.weolab.tms_service.resumedata.entity.ResumeDataDoc.java

package jp.co.basenet.weolab.tms_service.resumedata.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 履歴書データドキュメント（MongoDB 用）
 * フィールド名は英語のまま（Lombok による getter/setter も英語）
 */
@Document(collection = "resume")
@Data
public class ResumeDataDoc {

    /**
     * 人材ID（主キー）
     */
    @Id
    private String talentId;

    /**
     * 履歴書データ（JSON文字列）
     */
    private String resumeData;

    /**
     * 作成日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;
}