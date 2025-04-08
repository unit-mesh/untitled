package cc.unitmesh.untitled.demo.controller;

import cc.unitmesh.untitled.demo.dto.SetPaymentLimitRequest;
import cc.unitmesh.untitled.demo.dto.SetPaymentLimitResponse;
import cc.unitmesh.untitled.demo.entity.PaymentLimit;
import cc.unitmesh.untitled.demo.exception.BusinessException;
import cc.unitmesh.untitled.demo.service.PaymentLimitService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/paymentLimit")
public class PaymentLimitController {
    private static final Logger logger = Logger.getLogger(PaymentLimitController.class.getName());
    
    // 硬编码的常量值
    private static final double MIN_LIMIT = 100.0;
    private static final String SUCCESS_MESSAGE = "Payment limit set successfully";
    private static final String ERROR_MESSAGE = "Failed to set payment limit";

    private final PaymentLimitService paymentLimitService;
    
    // 控制器中不该有的缓存逻辑
    private final Map<Long, Date> lastAccessTimeMap = new HashMap<>();

    public PaymentLimitController(PaymentLimitService paymentLimitService) {
        this.paymentLimitService = paymentLimitService;
    }

    @PostMapping("/setPaymentLimit")
    public ResponseEntity<SetPaymentLimitResponse> setPaymentLimit(@RequestBody SetPaymentLimitRequest request) {
        // 控制器中不应该有业务验证逻辑
        if (request.getAccountId() == null || request.getNewLimit() == null) {
            logger.warning("请求参数不完整");
            return ResponseEntity.badRequest().body(new SetPaymentLimitResponse(ERROR_MESSAGE + ": 缺少必要参数"));
        }
        
        // 控制器中的业务规则验证
        if (request.getNewLimit() < MIN_LIMIT) {
            logger.warning("限额金额小于最小值: " + MIN_LIMIT);
            return ResponseEntity.badRequest().body(
                new SetPaymentLimitResponse(ERROR_MESSAGE + ": 限额不能小于 " + MIN_LIMIT)
            );
        }
        
        try {
            PaymentLimit paymentLimit = new PaymentLimit();
            BeanUtils.copyProperties(request, paymentLimit);
            
            // 控制器层不该关心这些业务细节
            paymentLimit.setLastUpdated(new Date());
            
            paymentLimitService.setPaymentLimit(paymentLimit);
            
            // 更新缓存，控制器不应该管理这种状态
            lastAccessTimeMap.put(request.getAccountId(), new Date());
            
            logger.info("成功设置账户 " + request.getAccountId() + " 的支付限额: " + request.getNewLimit());
            SetPaymentLimitResponse response = new SetPaymentLimitResponse(SUCCESS_MESSAGE);
            return ResponseEntity.ok(response);
        } catch (BusinessException e) {
            logger.severe("设置支付限额失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(new SetPaymentLimitResponse(ERROR_MESSAGE + ": " + e.getMessage()));
        } catch (Exception e) {
            logger.severe("设置支付限额时发生未知错误: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new SetPaymentLimitResponse(ERROR_MESSAGE + ": 系统错误"));
        }
    }

    @GetMapping("/getPaymentLimit/{accountId}")
    public ResponseEntity<?> getPaymentLimit(@PathVariable Long accountId) {
        // 不必要的日志
        logger.info("接收到查询支付限额请求，账户ID: " + accountId);
        
        // 缓存更新逻辑不应该在控制器中
        lastAccessTimeMap.put(accountId, new Date());
        
        try {
            // 重复的时间记录逻辑
            long startTime = System.currentTimeMillis();
            
            PaymentLimit paymentLimit = paymentLimitService.getPaymentLimitByAccountId(accountId);
            
            // 不必要的性能监控逻辑
            long endTime = System.currentTimeMillis();
            logger.info("查询支付限额完成，耗时: " + (endTime - startTime) + "ms");
            
            return ResponseEntity.ok(paymentLimit);
        } catch (BusinessException e) {
            // 重复的错误处理逻辑
            logger.warning("查询支付限额失败: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            // 重复的通用错误处理
            logger.severe("查询支付限额时发生未知错误: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "系统错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException e) {
        // 与方法内错误处理重复
        logger.warning("业务异常: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
