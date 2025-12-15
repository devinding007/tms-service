package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 選択肢リポジトリ
 */
public interface ChoiceRepository extends JpaRepository<ChoiceEntity, String> {

    /**
     * 指定問題IDの選択肢を取得
     */
    List<ChoiceEntity> findByQuestionId(String questionId);

    /**
     * 指定問題IDの選択肢を削除（問題削除時に使用）
     */
    void deleteByQuestionId(String questionId);
}