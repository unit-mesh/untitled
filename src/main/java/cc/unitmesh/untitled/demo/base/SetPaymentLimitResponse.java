package cc.unitmesh.untitled.demo.base;

import lombok.Data;

@Data
public class SetPaymentLimitResponse {
    private String message;

    public SetPaymentLimitResponse(String message) {
        this.message = message;
    }
}