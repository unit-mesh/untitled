package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.base.SetPaymentLimitRequest;
import cc.unitmesh.untitled.demo.base.SetPaymentLimitResponse;
import cc.unitmesh.untitled.demo.entity.PaymentLimit;
import cc.unitmesh.untitled.demo.exception.BusinessException;
import cc.unitmesh.untitled.demo.service.PaymentLimitService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;

@RestController
@RequestMapping("/paymentLimit")
public class PaymentLimitController {

    private final PaymentLimitService paymentLimitService;

    public PaymentLimitController(PaymentLimitService paymentLimitService) {
        this.paymentLimitService = paymentLimitService;
    }

    @PostMapping("/setPaymentLimit")
    public ResponseEntity<SetPaymentLimitResponse> setPaymentLimit(@RequestBody SetPaymentLimitRequest request) {
        PaymentLimit paymentLimit = new PaymentLimit();
        BeanUtils.copyProperties(request, paymentLimit);
        paymentLimitService.setPaymentLimit(paymentLimit);
        SetPaymentLimitResponse response = new SetPaymentLimitResponse("Payment limit set successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPaymentLimit/{accountId}")
    public ResponseEntity<PaymentLimit> getPaymentLimit(@PathVariable Long accountId) {
        try {
            PaymentLimit paymentLimit = paymentLimitService.getPaymentLimitByAccountId(accountId);
            return ResponseEntity.ok(paymentLimit);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
