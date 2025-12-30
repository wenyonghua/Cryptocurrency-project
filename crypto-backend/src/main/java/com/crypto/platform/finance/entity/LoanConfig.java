package com.crypto.platform.finance.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("loan_config")
public class LoanConfig implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String loanName;
    private String currency;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal dailyRate;
    private Integer maxDays;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

