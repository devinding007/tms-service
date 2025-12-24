package jp.co.basenet.weolab.tms_service.exam.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExamPaperQuestion {
    private String 試験用紙問題ＩＤ;
    private String 試験用紙ＩＤ;
    private String 問題文章;
    private Integer 難易度;
    private String スキル;
    private String 模範回答;
    private String 模範回答理由;
    private Integer 自動生成フラグ;
    private List<Choice> 選択肢;
}