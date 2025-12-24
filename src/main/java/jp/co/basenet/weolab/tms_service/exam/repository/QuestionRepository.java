// QuestionRepository.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.QuestionDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
        extends MongoRepository<QuestionDoc, String>, QuestionRepositoryCustom {
    // カスタムメソッドは QuestionRepositoryCustom から継承
}