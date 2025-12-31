package com.crypto.platform.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 首页仪表盘统计数据 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class DashboardStatisticsDTO {
    
    /**
     * 平台总收入
     */
    private BigDecimal totalRevenue;
    
    /**
     * 玩家数量
     */
    private Integer playerCount;
    
    /**
     * 总充值金额
     */
    private BigDecimal totalDeposit;
    
    /**
     * 提现金额
     */
    private BigDecimal totalWithdraw;
    
    /**
     * 充值趋势数据（最近7天）
     */
    private List<TrendDataDTO> depositTrend;
    
    /**
     * 提现趋势数据（最近7天）
     */
    private List<TrendDataDTO> withdrawTrend;
    
    /**
     * 趋势数据内部类
     */
    @Data
    public static class TrendDataDTO {
        /**
         * 日期
         */
        private String date;
        
        /**
         * 金额
         */
        private BigDecimal amount;
    }
}

