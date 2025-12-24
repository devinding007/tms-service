package jp.co.basenet.weolab.tms_service.proposal.dto;

import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseResult;
import jp.co.basenet.weolab.tms_service.personeltalents.dto.TalentDTO;

import java.util.List;

/**
 * 要員提案情報DTO
 * 前端のProposalインターフェースと完全に一致するフィールド構成
 * ※ フィールド名は「提案ID」（半角D）であることに注意
 */
public class Proposal {
    private String 提案ID;
    private String 提案名;
    private String 募集要項;
    private List<TalentDTO> 対象人材;
    private List<ProposalAnalyseResult> 分析結果;

    public String get提案ID() {
        return 提案ID;
    }

    public void set提案ID(String 提案ID) {
        this.提案ID = 提案ID;
    }

    public String get提案名() {
        return 提案名;
    }

    public void set提案名(String 提案名) {
        this.提案名 = 提案名;
    }

    public String get募集要項() {
        return 募集要項;
    }

    public void set募集要項(String 募集要項) {
        this.募集要項 = 募集要項;
    }

    public List<TalentDTO> get対象人材() {
        return 対象人材;
    }

    public void set対象人材(List<TalentDTO> 対象人材) {
        this.対象人材 = 対象人材;
    }

    public List<ProposalAnalyseResult> get分析結果() {
        return 分析結果;
    }

    public void set分析結果(List<ProposalAnalyseResult> 分析結果) {
        this.分析結果 = 分析結果;
    }
}