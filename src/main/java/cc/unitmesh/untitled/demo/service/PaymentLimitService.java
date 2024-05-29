package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.entity.PaymentLimit;
import cc.unitmesh.untitled.demo.exception.BusinessException;
import cc.unitmesh.untitled.demo.repository.PaymentLimitRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentLimitService {
    PaymentLimitRepository paymentLimitRepository;

    public PaymentLimitService(PaymentLimitRepository paymentLimitRepository) {
        this.paymentLimitRepository = paymentLimitRepository;
    }

    public PaymentLimit setPaymentLimit(PaymentLimit paymentLimit) {
        // Add business logic here
        return paymentLimitRepository.save(paymentLimit);
    }

    public PaymentLimit getPaymentLimitByAccountId(Long accountId) throws BusinessException {
        // Add business logic here
        return paymentLimitRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Payment limit not found"));
    }
}