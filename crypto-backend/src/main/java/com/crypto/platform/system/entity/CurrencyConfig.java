package com.crypto.platform.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("currency_config")
public class CurrencyConfig implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String currency;
    private String name;
    private String icon;
    private Integer decimals;
    private BigDecimal minDeposit;
    private BigDecimal minWithdraw;
    private BigDecimal withdrawFee;
    private Integer withdrawFeeType;
    private BigDecimal dailyWithdrawLimit;
    private Integer depositEnabled;
    private Integer withdrawEnabled;
    private Integer tradeEnabled;
    private Integer sortOrder;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

