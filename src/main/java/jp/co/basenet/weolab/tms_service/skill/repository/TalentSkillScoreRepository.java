package jp.co.basenet.weolab.tms_service.skill.repository;

import jp.co.basenet.weolab.tms_service.skill.entity.TalentSkillScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * スキルスコアのリポジトリ
 */
@Repository
public interface TalentSkillScoreRepository extends JpaRepository<TalentSkillScore, String> {

    /**
     * 指定された人材IDのスキルスコアを全件取得
     */
    List<TalentSkillScore> findByTalentId(String talentId);

    /**
     * 指定された人材IDのスキルスコアを全削除（全量上書き用）
     */
    @Modifying
    @Query("DELETE FROM TalentSkillScore t WHERE t.talentId = :talentId")
    void deleteByTalentId(String talentId);


    @Query("SELECT DISTINCT t.skillName FROM TalentSkillScore t")
    List<String> findAllSkillNames();
}