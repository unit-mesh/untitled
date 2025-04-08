package cc.unitmesh.untitled.demo.repository;

import cc.unitmesh.untitled.demo.entity.PaymentLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLimitRepository extends JpaRepository<PaymentLimit, Long> {

    PaymentLimit findByAccountId(Long accountId);
}