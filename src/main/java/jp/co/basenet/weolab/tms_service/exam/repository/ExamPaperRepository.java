package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamPaperRepository
        extends MongoRepository<ExamPaperDoc, String>, ExamPaperRepositoryCustom {
    // カスタムメソッドは自動継承
}