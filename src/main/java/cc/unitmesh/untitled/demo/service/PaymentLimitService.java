package cc.unitmesh.untitled.demo.service;

import cc.unitmesh.untitled.demo.entity.PaymentLimit;
import cc.unitmesh.untitled.demo.exception.BusinessException;
import cc.unitmesh.untitled.demo.repository.PaymentLimitRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class PaymentLimitService {
    private static final Logger logger = Logger.getLogger(PaymentLimitService.class.getName());
    PaymentLimitRepository paymentLimitRepository;
    
    // 硬编码常量
    private static final double DEFAULT_LIMIT = 5000.0;
    private static final double MAX_LIMIT = 100000.0;
    private static final double VIP_LIMIT = 500000.0;

    public PaymentLimitService(PaymentLimitRepository paymentLimitRepository) {
        this.paymentLimitRepository = paymentLimitRepository;
    }

    // 超长方法，混合了多种职责
    public PaymentLimit setPaymentLimit(PaymentLimit paymentLimit) throws BusinessException {
        // 日志记录
        logger.info("开始设置账户ID: " + paymentLimit.getAccountId() + " 的支付限额");
        
        // 数据验证逻辑 (应该抽取为单独方法)
        if (paymentLimit.getAccountId() == null) {
            logger.severe("账户ID不能为空");
            throw new BusinessException("账户ID不能为空");
        }
        
        if (paymentLimit.getLimitAmount() == null) {
            logger.info("未指定限额，设置为默认值：" + DEFAULT_LIMIT);
            paymentLimit.setLimitAmount(DEFAULT_LIMIT);
        }
        
        // 业务规则检查 (应该抽取为单独方法)
        boolean isVipAccount = checkIfVipAccount(paymentLimit.getAccountId());
        
        if (isVipAccount) {
            if (paymentLimit.getLimitAmount() > VIP_LIMIT) {
                logger.warning("VIP账户限额超过上限: " + VIP_LIMIT);
                paymentLimit.setLimitAmount(VIP_LIMIT);
            }
        } else {
            if (paymentLimit.getLimitAmount() > MAX_LIMIT) {
                logger.warning("普通账户限额超过上限: " + MAX_LIMIT);
                paymentLimit.setLimitAmount(MAX_LIMIT);
            }
        }
        
        // 数据库操作前的重复检查 (应该使用数据库唯一约束)
        PaymentLimit existingLimit = paymentLimitRepository.findByAccountId(paymentLimit.getAccountId());
        if (existingLimit != null) {
            logger.info("更新已存在的限额记录");
            existingLimit.setLimitAmount(paymentLimit.getLimitAmount());
            existingLimit.setLastUpdated(new Date());
            return paymentLimitRepository.save(existingLimit);
        } else {
            logger.info("创建新的限额记录");
            paymentLimit.setLastUpdated(new Date());
            return paymentLimitRepository.save(paymentLimit);
        }
    }

    // 重复的验证逻辑
    public PaymentLimit getPaymentLimitByAccountId(Long accountId) throws BusinessException {
        // 重复的验证逻辑
        if (accountId == null) {
            logger.severe("查询支付限额失败：账户ID不能为空");
            throw new BusinessException("账户ID不能为空");
        }
        
        logger.info("查询账户ID: " + accountId + " 的支付限额");
        
        // 数据获取
        PaymentLimit limit = paymentLimitRepository.findById(accountId)
                .orElse(null);
                
        if (limit == null) {
            // 记录错误
            logger.warning("未找到账户ID: " + accountId + " 的支付限额记录");
            
            // 创建默认限额 (混合了创建逻辑，应该分离)
            boolean isVip = checkIfVipAccount(accountId);
            PaymentLimit defaultLimit = new PaymentLimit();
            defaultLimit.setAccountId(accountId);
            defaultLimit.setLimitAmount(isVip ? VIP_LIMIT : DEFAULT_LIMIT);
            defaultLimit.setLastUpdated(new Date());
            logger.info("为账户创建默认限额: " + defaultLimit.getLimitAmount());
            return paymentLimitRepository.save(defaultLimit);
        }
        
        return limit;
    }
    
    // 硬编码的业务逻辑
    private boolean checkIfVipAccount(Long accountId) {
        // 硬编码VIP账户判断逻辑 (应该从配置或数据库读取)
        return accountId != null && (
            accountId == 10001L || 
            accountId == 10002L || 
            accountId == 10003L || 
            accountId.toString().startsWith("999")
        );
    }
}
