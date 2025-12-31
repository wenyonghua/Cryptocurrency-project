package com.crypto.platform.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 平台统计数据实体
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
@TableName("platform_statistics")
public class PlatformStatistics implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 统计ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 统计日期
     */
    private LocalDate statDate;
    
    /**
     * 总用户数
     */
    private Integer totalUsers;
    
    /**
     * 新增用户数
     */
    private Integer newUsers;
    
    /**
     * 活跃用户数
     */
    private Integer activeUsers;
    
    /**
     * 总充值金额
     */
    private BigDecimal totalDeposit;
    
    /**
     * 总提现金额
     */
    private BigDecimal totalWithdraw;
    
    /**
     * 总交易量
     */
    private BigDecimal totalTradeVolume;
    
    /**
     * 总利润（平台收入）
     */
    private BigDecimal totalProfit;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

