// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamRunRepositoryCustom.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamRunRepositoryCustom {
    Page<ExamRunDoc> findByStatus(Integer status, Pageable pageable);
    Page<ExamRunDoc> findByParticipantNameContaining(String participantName, Pageable pageable);
    Page<ExamRunDoc> findByExamRunIdContaining(String examRunId, Pageable pageable);
}