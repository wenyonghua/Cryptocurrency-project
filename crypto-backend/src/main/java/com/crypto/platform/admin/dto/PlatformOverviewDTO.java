package com.crypto.platform.admin.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 平台概要信息 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class PlatformOverviewDTO {
    
    /**
     * 总用户数
     */
    private Integer totalUsers;
    
    /**
     * 今日新增用户
     */
    private Integer todayNewUsers;
    
    /**
     * 今日活跃用户
     */
    private Integer todayActiveUsers;
    
    /**
     * 总充值金额
     */
    private BigDecimal totalDeposit;
    
    /**
     * 今日充值金额
     */
    private BigDecimal todayDeposit;
    
    /**
     * 总提现金额
     */
    private BigDecimal totalWithdraw;
    
    /**
     * 今日提现金额
     */
    private BigDecimal todayWithdraw;
    
    /**
     * 总交易量
     */
    private BigDecimal totalTradeVolume;
    
    /**
     * 今日交易量
     */
    private BigDecimal todayTradeVolume;
    
    /**
     * 总利润
     */
    private BigDecimal totalProfit;
    
    /**
     * 今日利润
     */
    private BigDecimal todayProfit;
    
    /**
     * VIP用户数
     */
    private Integer vipUserCount;
    
    /**
     * 已认证用户数
     */
    private Integer verifiedUserCount;
}

