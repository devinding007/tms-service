// ファイルパス: jp.co.basenet.weolab.tms_service.resumedata.repository.ResumeDataRepository.java

package jp.co.basenet.weolab.tms_service.resumedata.repository;

import jp.co.basenet.weolab.tms_service.resumedata.entity.ResumeDataDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 履歴書データのリポジトリ（MongoDB 版）
 */
@Repository
public interface ResumeDataRepository extends MongoRepository<ResumeDataDoc, String> {
    // findById, save などが自動提供される
}