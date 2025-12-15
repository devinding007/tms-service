package jp.co.basenet.weolab.tms_service.personeltalents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleResponse {
    private String id;
    private String name;
    private int value;
}