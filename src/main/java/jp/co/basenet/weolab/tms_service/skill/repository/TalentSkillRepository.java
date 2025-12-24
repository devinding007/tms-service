// ファイルパス: jp.co.basenet.weolab.tms_service.skill.repository.TalentSkillRepository.java

package jp.co.basenet.weolab.tms_service.skill.repository;

import jp.co.basenet.weolab.tms_service.skill.entity.TalentSkillDoc;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * スキル情報のリポジトリ（MongoDB 版）
 */
@Repository
public interface TalentSkillRepository extends MongoRepository<TalentSkillDoc, String> {

    /**
     * 全てのスキル名を重複なしで取得する
     * Aggregation Pipeline:
     * 1. $unwind: skills 配列を展開
     * 2. $group: 全 skillName を1つのセットに集約（重複排除）
     * 3. $project: 結果を配列形式に整形
     */
    @Aggregation(pipeline = {
            "{ '$unwind': '$skills' }",
            "{ '$group': { '_id': null, 'skillNames': { '$addToSet': '$skills.skillName' } } }",
            "{ '$project': { '_id': 0, 'skillNames': 1 } }"
    })
    List<Map<String, Object>> aggregateAllSkillNames();
}