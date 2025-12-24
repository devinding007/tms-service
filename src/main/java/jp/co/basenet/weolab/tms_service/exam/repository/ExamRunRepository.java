// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamRunRepository.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRunRepository
        extends MongoRepository<ExamRunDoc, String>, ExamRunRepositoryCustom {
}