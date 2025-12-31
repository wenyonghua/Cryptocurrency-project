package com.crypto.platform.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 每日数据 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class DailyDataDTO {
    
    /**
     * 日期
     */
    private LocalDate statDate;
    
    /**
     * 新增用户数
     */
    private Integer newUsers;
    
    /**
     * 活跃用户数
     */
    private Integer activeUsers;
    
    /**
     * 充值金额
     */
    private BigDecimal depositAmount;
    
    /**
     * 提现金额
     */
    private BigDecimal withdrawAmount;
    
    /**
     * 交易量
     */
    private BigDecimal tradeVolume;
    
    /**
     * 利润
     */
    private BigDecimal profit;
    
    /**
     * 充值笔数
     */
    private Integer depositCount;
    
    /**
     * 提现笔数
     */
    private Integer withdrawCount;
    
    /**
     * 交易笔数
     */
    private Integer tradeCount;
}

