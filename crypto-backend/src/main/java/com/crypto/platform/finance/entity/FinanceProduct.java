package com.crypto.platform.finance.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("finance_product")
public class FinanceProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String productName;
    private String currency;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal annualRate;
    private Integer durationDays;
    private BigDecimal totalQuota;
    private BigDecimal remainingQuota;
    private Integer status; // 0-下架，1-上架
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

