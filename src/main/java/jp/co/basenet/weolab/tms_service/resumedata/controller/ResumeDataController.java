    package jp.co.basenet.weolab.tms_service.resumedata.controller;

    import com.fasterxml.jackson.core.JsonProcessingException;
    import jp.co.basenet.weolab.tms_service.resumedata.dto.ResumeData;
    import jp.co.basenet.weolab.tms_service.resumedata.service.ResumeDataService;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    /**
     * 履歴書データの REST API コントローラー
     */
    @Slf4j
    @RestController
    @RequestMapping("/api/resume")
    @RequiredArgsConstructor
    public class ResumeDataController {

        private final ResumeDataService resumeDataService;

        /**
         * GET /api/resume/{talentId}
         * 指定された人材IDの履歴書データを取得する
         */
        @GetMapping("/{talentId}")
        public ResponseEntity<ResumeData> getResume(@PathVariable String talentId) {
            log.info("GET /api/resume/{} 呼び出し", talentId);

            ResumeData resume = resumeDataService.getResumeByTalentId(talentId);


            return ResponseEntity.ok(resume);
        }

        /**
         * PUT /api/resume/{talentId}
         * 履歴書データを保存する（全量上書き）
         */
        @PutMapping("/{talentId}")
        public ResponseEntity<Void> saveResume(
                @PathVariable String talentId,
                @RequestBody ResumeData resumeData  // ✅ DTO使用（日本語フィールド）
        ) throws JsonProcessingException {

    //        log.info("====================================");
    //        log.info("PUT /api/resume/{} 呼び出し", talentId);
    //        log.info("====================================");
    //
    //        // 基本情報ログ（日本語getterを使用）
    //        log.info("受信データ:");
    //        log.info("  人材ＩＤ: {}", resumeData.get人材ＩＤ());
    //        log.info("  氏名: {}", resumeData.get氏名());
    //        log.info("  出生年月: {}", resumeData.get出生年月());
    //
    //        // IDの整合性チェック
    //        if (!talentId.equals(resumeData.get人材ＩＤ())) {
    //            log.error("❌ IDが不一致: path={}, body={}",
    //                    talentId, resumeData.get人材ＩＤ());
    //            return ResponseEntity.badRequest().build();
    //        }
    //
    //        // AI分析結果の詳細ログ
    //        boolean hasAI = resumeData.getAI分析結果() != null;
    //        log.info("AI分析結果: {}", hasAI ? "✅ あり" : "❌ なし");
    //
    //        if (!hasAI) {
    //            log.warn("⚠️ AI分析結果が含まれていません");
    //            log.warn("   フロントエンドで「AI分析結果」にチェックを入れていますか？");
    //        } else {
    //            var analysis = resumeData.getAI分析結果();
    //            log.info("--- AI分析の内容 ---");
    //            log.info("一言集約: {}", analysis.get一言集約());
    //            log.info("一番得意な領域: {}", analysis.get一番得意な領域());
    //
    //            if (analysis.get強み領域() != null) {
    //                log.info("強み領域数: {}", analysis.get強み領域().size());
    //            }
    //            if (analysis.getアサイン適正() != null) {
    //                log.info("アサイン適正数: {}", analysis.getアサイン適正().size());
    //            }
    //            if (analysis.getスキル採点() != null) {
    //                log.info("スキル採点数: {}", analysis.getスキル採点().size());
    //
    //                // 最初の3件を表示
    //                analysis.getスキル採点().stream().limit(3).forEach(score ->
    //                        log.info("  - {} : {}点", score.getスキル名(), score.get点数())
    //                );
    //            }
    //        }

            // 保存実行
            try {
                resumeDataService.saveResume(talentId, resumeData);
                log.info("✅ 保存完了");
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                log.error("❌ 保存中にエラー発生", e);
                throw e;
            }
        }

        /**
         * POST /api/resume/analyse
         * AI分析を実行する（実装はフロントエンド側）
         */
        @PostMapping("/analyse")
        public ResponseEntity<ResumeData.SkillSummary> analyseResume(
                @RequestBody ResumeData resumeData) {

            log.info("POST /api/resume/analyse 呼び出し");
            log.info("人材ＩＤ: {}", resumeData.get人材ＩＤ());

            // 実際のAI分析はフロントエンド側で実装されている想定
            log.warn("⚠️ この端点は現在使用されていません");

            return ResponseEntity.ok(new ResumeData.SkillSummary());
        }
    }