
package jp.co.basenet.weolab.tms_service.personeltalents.repository;

import jp.co.basenet.weolab.tms_service.personeltalents.entity.TalentDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentdocRepository extends MongoRepository<TalentDoc, String> {

    /**
     * 查询未删除（deletedFlag = false 或 null）的记录
     * MongoDB 中 null 和 false 视为不同，需用 $or
     */
    @Query("{ $or: [ { 'deletedFlag': false }, { 'deletedFlag': { $exists: false } } ] }")
    List<TalentDoc> findActiveTalents();
}