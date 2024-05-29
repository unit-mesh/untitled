package cc.unitmesh.untitled.demo.base;

import lombok.Data;

@Data
public class SetPaymentLimitRequest {
    private Long accountId;
    private Double newLimit;
}