// ファイル: jp.co.basenet.weolab.tms_service.exam.repository.ExamPaperRepositoryCustom.java

package jp.co.basenet.weolab.tms_service.exam.repository;

import jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 試験用紙リポジトリのカスタムメソッド定義
 * 動的検索・論理削除対応クエリを提供
 */
public interface ExamPaperRepositoryCustom {

    /**
     * 論理削除されていない試験用紙を paperId で取得
     */
    Optional<ExamPaperDoc> findByPaperIdAndDeletedAtIsNull(String paperId);

    /**
     * 試験用紙名または説明でキーワード検索（論理削除除外）
     */
    Page<ExamPaperDoc> findByKeyword(String keyword, Pageable pageable);

    /**
     * 試験用紙名と説明でフィルタ検索（論理削除除外）
     */
    Page<ExamPaperDoc> findByFilters(String name, String description, Pageable pageable);
}