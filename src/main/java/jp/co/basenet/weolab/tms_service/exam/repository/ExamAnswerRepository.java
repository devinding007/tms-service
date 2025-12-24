// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamAnswerRepository.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamAnswerDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 試験回答リポジトリ（MongoDB 版）
 */
@Repository
public interface ExamAnswerRepository
        extends MongoRepository<ExamAnswerDoc, String>, ExamAnswerRepositoryCustom {

    List<ExamAnswerDoc> findByExamRunId(String examRunId);
}