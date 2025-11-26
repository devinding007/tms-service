package jp.co.basenet.weolab.tms_service.exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenerationRequest {

    String levelFrom;
    String levelTo;
    String skills;
    String jobPosting;
    String number;
    
}
