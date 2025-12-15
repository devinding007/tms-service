// tms_service/exam/dto/QuestionListResponse.java
package jp.co.basenet.weolab.tms_service.exam.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionListResponse {
    private List<Question> items;
    private long total;
}