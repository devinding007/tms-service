// src/main/java/.../common/dto/ApiListResult.java
package jp.co.basenet.weolab.tms_service.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiListResult<T> {
    private List<T> items;
    private long total;
}