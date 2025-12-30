package com.crypto.platform.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("trading_pair")
public class TradingPair implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String symbol;
    private String baseCurrency;
    private String quoteCurrency;
    private Integer pricePrecision;
    private Integer amountPrecision;
    private BigDecimal minAmount;
    private BigDecimal minTotal;
    private BigDecimal makerFee;
    private BigDecimal takerFee;
    private Integer status;
    private Integer sortOrder;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

