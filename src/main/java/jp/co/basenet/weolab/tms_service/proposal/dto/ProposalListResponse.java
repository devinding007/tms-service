package jp.co.basenet.weolab.tms_service.proposal.dto;

import jp.co.basenet.weolab.tms_service.common.dto.ApiListResult;

/**
 * 提案一覧レスポンスDTO
 * ApiListResult<Proposal> の形式でページネーションをサポート
 */
public class ProposalListResponse extends ApiListResult<Proposal> {
    // 特別なフィールドは不要。親クラスの items, total を使用
}