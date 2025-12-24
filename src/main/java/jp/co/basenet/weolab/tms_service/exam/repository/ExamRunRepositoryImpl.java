// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamRunRepositoryImpl.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamRunRepositoryImpl implements ExamRunRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<ExamRunDoc> findByStatus(Integer status, Pageable pageable) {
        Criteria criteria = Criteria.where("status").is(status);
        return getPageResult(criteria, pageable);
    }

    @Override
    public Page<ExamRunDoc> findByParticipantNameContaining(String participantName, Pageable pageable) {
        Criteria criteria = Criteria.where("participantName").regex(participantName, "i");
        return getPageResult(criteria, pageable);
    }

    @Override
    public Page<ExamRunDoc> findByExamRunIdContaining(String examRunId, Pageable pageable) {
        Criteria criteria = Criteria.where("examRunId").regex(examRunId, "i");
        return getPageResult(criteria, pageable);
    }

    private Page<ExamRunDoc> getPageResult(Criteria criteria, Pageable pageable) {
        Query query = Query.query(criteria).with(pageable);
        List<ExamRunDoc> results = mongoTemplate.find(query, ExamRunDoc.class);
        long total = mongoTemplate.count(Query.query(criteria), ExamRunDoc.class);
        return new PageImpl<>(results, pageable, total);
    }
}