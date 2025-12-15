package jp.co.basenet.weolab.tms_service.resumedata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * フロントエンドの ResumeData TypeScript インターフェースに対応する DTO
 *
 * 注意: このクラスはフロントエンドとの通信用なので、フィールド名は日本語のまま
 * Lombokが生成するメソッド例:
 * - get人材ＩＤ() / set人材ＩＤ(String)
 * - get氏名() / set氏名(String)
 * - getAI分析結果() / setAI分析結果(SkillSummary)
 */
@Data
public class ResumeData {

    @JsonProperty("人材ＩＤ")
    private String 人材ＩＤ;

    @JsonProperty("氏名")
    private String 氏名;

    @JsonProperty("出生年月")
    private String 出生年月;

    @JsonProperty("国籍")
    private String 国籍;

    @JsonProperty("来日年月")
    private String 来日年月;

    @JsonProperty("最寄駅")
    private String 最寄駅;

    @JsonProperty("アピールポイント")
    private String アピールポイント;

    @JsonProperty("言語")
    private List<ResumeLanguage> 言語;

    @JsonProperty("学歴")
    private List<ResumeEducation> 学歴;

    @JsonProperty("資格")
    private List<String> 資格;

    @JsonProperty("スキル")
    private List<ResumeSkill> スキル;

    @JsonProperty("業務歴")
    private List<ResumeExperience> 業務歴;

    /**
     * AI分析結果
     * 重要: "AI分析結果" のみ受け付ける（"ai分析結果" は無視）
     */
    @JsonProperty("AI分析結果")
    private SkillSummary AI分析結果;

    // --- 嵌套クラス: 言語 ---
    @Data
    public static class ResumeLanguage {
        @JsonProperty("言語名")
        private String 言語名;

        @JsonProperty("読み")
        private String 読み;

        @JsonProperty("書き")
        private String 書き;

        @JsonProperty("会話")
        private String 会話;
    }

    // --- 嵌套クラス: 学歴 ---
    @Data
    public static class ResumeEducation {
        @JsonProperty("開始年月")
        private String 開始年月;

        @JsonProperty("終了年月")
        private String 終了年月;

        @JsonProperty("学校")
        private String 学校;

        @JsonProperty("専門")
        private String 専門;

        @JsonProperty("学位")
        private String 学位;
    }

    // --- 嵌套クラス: スキル ---
    @Data
    public static class ResumeSkill {
        @JsonProperty("スキル名")
        private String スキル名;

        @JsonProperty("レベル")
        private String レベル;
    }

    // --- 嵌套クラス: 業務歴 ---
    @Data
    public static class ResumeExperience {
        @JsonProperty("期間開始")
        private String 期間開始;

        @JsonProperty("期間終了")
        private String 期間終了;

        @JsonProperty("担当業務詳細")
        private String 担当業務詳細;

        @JsonProperty("チーム規模")
        private String チーム規模;

        @JsonProperty("利用技術")
        private List<String> 利用技術;

        @JsonProperty("役割")
        private String 役割;
    }

    // --- 嵌套クラス: AI分析結果 ---
    @Data
    public static class SkillSummary {
        @JsonProperty("一言集約")
        private String 一言集約;

        @JsonProperty("強み領域")
        private List<Area> 強み領域;

        @JsonProperty("一番得意な領域")
        private String 一番得意な領域;

        @JsonProperty("アサイン適正")
        private List<String> アサイン適正;

        @JsonProperty("伸ばすと効く")
        private List<Area> 伸ばすと効く;

        @JsonProperty("スキル採点")
        private List<SkillScore> スキル採点;

        @Data
        public static class Area {
            @JsonProperty("領域")
            private String 領域;

            @JsonProperty("理由")
            private String 理由;
        }

        @Data
        public static class SkillScore {
            @JsonProperty("スキル名")
            private String スキル名;

            @JsonProperty("点数")
            private Integer 点数;
        }
    }
}