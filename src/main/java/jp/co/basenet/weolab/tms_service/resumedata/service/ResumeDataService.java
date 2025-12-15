package jp.co.basenet.weolab.tms_service.resumedata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.basenet.weolab.tms_service.resumedata.dto.ResumeData;
import jp.co.basenet.weolab.tms_service.resumedata.entity.ResumeDataEntity;
import jp.co.basenet.weolab.tms_service.resumedata.repository.ResumeDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 履歴書データのサービス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeDataService {

    private final ResumeDataRepository resumeDataRepository;
    private final ObjectMapper objectMapper;

    /**
     * 指定された人材IDの履歴書データを取得する
     *
     * @param talentId 人材ID
     * @return ResumeData オブジェクト（JSONをデシリアライズ）
     */
    public ResumeData getResumeByTalentId(String talentId) {
        log.info("=== 履歴書データ取得開始 ===");
        log.info("talentId: {}", talentId);

        // Entity取得（英語フィールド名: talentId）
        var entityOpt = resumeDataRepository.findById(talentId);
        if (entityOpt.isEmpty()) {
            log.warn("履歴書データが見つかりません");
            return null;
        }

        // Entity→DTO変換（英語 → 日本語）
        ResumeDataEntity entity = entityOpt.get();
        String json = entity.getResumeData();  // ✅ 英語getter

        if (json == null || json.isEmpty()) {
            log.warn("resume_data が空です");
            return null;
        }

        try {
            // JSON → ResumeData（日本語フィールド）
            ResumeData resumeData = objectMapper.readValue(json, ResumeData.class);

            log.info("取得成功: 氏名={}", resumeData.get氏名());  // ✅ 日本語getter
            log.info("AI分析結果の有無: {}", resumeData.getAI分析結果() != null);

            return resumeData;
        } catch (Exception e) {
            log.error("JSONデシリアライズエラー", e);
            throw new RuntimeException("Failed to parse resume data", e);
        }
    }

    /**
     * 履歴書データを保存する（全量上書き）
     *
     * @param talentId   人材ID
     * @param resumeData ResumeData オブジェクト（日本語フィールド）
     */
    @Transactional
    public void saveResume(String talentId, ResumeData resumeData) throws JsonProcessingException {
        log.info("=== 履歴書データ保存開始 ===");
        log.info("talentId: {}", talentId);
        log.info("氏名: {}", resumeData.get氏名());  // ✅ 日本語getter

        // AI分析結果の有無チェック
        boolean hasAI = resumeData.getAI分析結果() != null;
        log.info("AI分析結果: {}", hasAI ? "あり" : "なし");

        if (hasAI) {
            var analysis = resumeData.getAI分析結果();
            log.info("--- AI分析內容 ---");
            log.info("一言集約: {}", analysis.get一言集約());
            log.info("スキル採点数: {}",
                    analysis.getスキル採点() != null ? analysis.getスキル採点().size() : 0);
        }

        // 1. DTO → JSON（日本語フィールドを含むJSON文字列）
        String jsonData = objectMapper.writeValueAsString(resumeData);
        log.debug("JSON長: {} バイト", jsonData.length());

        // 2. Entity取得または新規作成（英語フィールド）
        ResumeDataEntity entity = resumeDataRepository.findById(talentId)
                .orElse(new ResumeDataEntity());

        // 3. Entityにデータ設定（英語setter使用）
        entity.setTalentId(talentId);      // ✅ 英語setter
        entity.setResumeData(jsonData);    // ✅ 英語setter

        // 4. データベースに保存
        try {
            resumeDataRepository.save(entity);
            log.info("✅ データベース保存成功");
        } catch (Exception e) {
            log.error("❌ データベース保存失敗", e);
            throw e;
        }
    }
}