package jp.co.basenet.weolab.tms_service.resumedata.repository;

import jp.co.basenet.weolab.tms_service.resumedata.entity.ResumeDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 履歴書データのリポジトリ
 */
@Repository
public interface ResumeDataRepository extends JpaRepository<ResumeDataEntity, String> {
    // JpaRepository により findById, save などが自動提供される
}