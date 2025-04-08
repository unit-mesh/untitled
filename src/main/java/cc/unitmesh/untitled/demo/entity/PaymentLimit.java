package cc.unitmesh.untitled.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tw_payment_limit")
@Data
public class PaymentLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", unique = true, nullable = false)
    private Long accountId;

    @Column(name = "limit_amount", nullable = false)
    private Double limitAmount;
    
    @Column(name = "last_updated")
    private Date lastUpdated;
    
    @Column(name = "limit_type")
    private String limitType;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // 实体中不应该有业务逻辑
    public boolean isLimitExceeded(Double amount) {
        return amount > limitAmount;
    }
    
    // 实体中不应该有格式化逻辑
    public String getFormattedLimit() {
        return String.format("¥%.2f", limitAmount);
    }
    
    // 实体中不应该有业务计算逻辑
    public Double calculateAvailableLimit(Double usedAmount) {
        return limitAmount - usedAmount;
    }
    
    // 实体不应该负责验证
    public boolean isValid() {
        return accountId != null && limitAmount != null && limitAmount > 0;
    }
    
    // 业务规则不应该在实体中
    @PrePersist
    @PreUpdate
    public void validateData() {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        
        if (limitAmount == null) {
            limitAmount = 1000.0; // 默认值
        }
        
        if (limitAmount < 0) {
            throw new IllegalArgumentException("Limit amount cannot be negative");
        }
        
        if (lastUpdated == null) {
            lastUpdated = new Date();
        }
        
        if (limitType == null) {
            limitType = "STANDARD";
        }
    }
}
