// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamAnswerRepositoryCustom.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import java.util.List;

/**
 * 試験回答リポジトリのカスタムメソッド
 */
public interface ExamAnswerRepositoryCustom {

    /**
     * 指定された paperQuestionId のいずれかを持つ回答が存在するか
     */
    boolean existsByPaperQuestionIdIn(List<String> paperQuestionIds);

    /**
     * 指定された試験実行IDの回答を全削除
     */
    void deleteByExamRunId(String examRunId);
}