// ファイル: jp.co.basenet.weolab.tms_service.proposal.repository.ProposalRepository.java

package jp.co.basenet.weolab.tms_service.proposal.repository;

import jp.co.basenet.weolab.tms_service.proposal.entity.ProposalDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 要員提案リポジトリ（MongoDB 版）
 */
@Repository
public interface ProposalRepository extends MongoRepository<ProposalDoc, String> {
    // 標準CRUDメソッドのみ使用
}