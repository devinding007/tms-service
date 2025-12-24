package jp.co.basenet.weolab.tms_service.proposal.controller;

import jp.co.basenet.weolab.tms_service.common.dto.ApiListResult;
import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseResult;
import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;
import jp.co.basenet.weolab.tms_service.proposal.dto.Proposal;
import jp.co.basenet.weolab.tms_service.proposal.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 要員提案のREST APIエンドポイント
 *
 * エンドポイント:
 * - GET  /api/proposals         : 提案一覧取得（ページネーション）
 * - POST /api/proposals         : 提案保存（新規作成・更新）
 * - DELETE /api/proposals/{id}  : 提案削除
 */
@RestController
@RequestMapping("/api/proposals")
public class ProposalCrudController {

    @Autowired
    private ProposalService proposalService;

    /**
     * 提案一覧を取得（ページネーション対応）
     *
     * クエリパラメータ:
     * - page: ページ番号（デフォルト: 1）
     * - size: ページサイズ（デフォルト: 10）
     */
    @GetMapping
    public ResponseEntity<ApiListResult<Proposal>> listProposals(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // ページ番号が1未満の場合は1に補正
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (size > 100) size = 100; // セキュリティ対策（過剰取得防止）

        ApiListResult<Proposal> result = proposalService.listProposals(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{proposalId}")
    public ResponseEntity<Proposal> getProposal(@PathVariable String proposalId) {
        Proposal proposal = proposalService.getProposal(proposalId);
        if (proposal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposal);
    }

    /**
     * 提案を保存（新規作成または更新）
     *
     * リクエストボディに Proposal DTO をJSONで送信
     * 提案IDが存在する場合は更新、そうでない場合は新規作成
     */
    @PostMapping
    public ResponseEntity<Proposal> saveProposal(@RequestBody Proposal proposal) {


        if (proposal.get提案ID() == null || proposal.get提案ID().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        Proposal saved = proposalService.saveProposal(proposal);
        return ResponseEntity.ok(saved);
    }

    /**
     * 提案を削除
     *
     * @param proposalId 削除対象の提案ID
     */
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable String proposalId) {
        proposalService.deleteProposal(proposalId);
        return ResponseEntity.noContent().build();
    }
}