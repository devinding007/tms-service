// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamAnswerRepositoryImpl.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamAnswerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 試験回答リポジトリのカスタム実装
 */
@Repository
public class ExamAnswerRepositoryImpl implements ExamAnswerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean existsByPaperQuestionIdIn(List<String> paperQuestionIds) {
        if (paperQuestionIds == null || paperQuestionIds.isEmpty()) {
            return false;
        }
        Criteria criteria = Criteria.where("paperQuestionId").in(paperQuestionIds);
        return mongoTemplate.exists(Query.query(criteria), ExamAnswerDoc.class);
    }

    @Override
    public void deleteByExamRunId(String examRunId) {
        if (examRunId == null || examRunId.isEmpty()) {
            return;
        }
        Criteria criteria = Criteria.where("examRunId").is(examRunId);
        mongoTemplate.remove(Query.query(criteria), ExamAnswerDoc.class);
    }
}