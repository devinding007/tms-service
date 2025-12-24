// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamPaperRepositoryImpl.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 試験用紙リポジトリのカスタム実装
 * Criteria + MongoTemplate で null 安全な動的クエリを構築
 */
@Repository
public class ExamPaperRepositoryImpl implements ExamPaperRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<ExamPaperDoc> findByPaperIdAndDeletedAtIsNull(String paperId) {
        Criteria criteria = Criteria.where("paperId").is(paperId)
                .and("deletedAt").is(null);
        ExamPaperDoc result = mongoTemplate.findOne(Query.query(criteria), ExamPaperDoc.class);
        return Optional.ofNullable(result);
    }

    @Override
    public Page<ExamPaperDoc> findByKeyword(String keyword, Pageable pageable) {
        Criteria criteria = Criteria.where("deletedAt").is(null);

        if (keyword != null && !keyword.trim().isEmpty()) {
            // 試験用紙名または説明にキーワードが含まれる
            Criteria nameCrit = Criteria.where("paperName").regex(keyword, "i");
            Criteria descCrit = Criteria.where("description").regex(keyword, "i");
            criteria.andOperator(
                    new Criteria().orOperator(nameCrit, descCrit)
            );
        }

        return executePageQuery(criteria, pageable);
    }

    @Override
    public Page<ExamPaperDoc> findByFilters(String name, String description, Pageable pageable) {
        Criteria criteria = Criteria.where("deletedAt").is(null);

        if (name != null && !name.trim().isEmpty()) {
            criteria.and("paperName").regex(name, "i");
        }
        if (description != null && !description.trim().isEmpty()) {
            criteria.and("description").regex(description, "i");
        }

        return executePageQuery(criteria, pageable);
    }

    /**
     * 共通のページング実行メソッド
     */
    private Page<ExamPaperDoc> executePageQuery(Criteria criteria, Pageable pageable) {
        Query query = Query.query(criteria).with(pageable);
        List<ExamPaperDoc> results = mongoTemplate.find(query, ExamPaperDoc.class);
        long total = mongoTemplate.count(Query.query(criteria), ExamPaperDoc.class);
        return new PageImpl<>(results, pageable, total);
    }
}