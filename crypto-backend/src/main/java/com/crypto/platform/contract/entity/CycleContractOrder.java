package com.crypto.platform.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("cycle_contract_order")
public class CycleContractOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNo;
    private Long userId;
    private String symbol;
    private Integer direction; // 1-看涨，2-看跌
    private BigDecimal amount;
    private Integer cycleType; // 1-1分钟，2-5分钟，3-15分钟，4-30分钟，5-1小时，6-4小时，7-1天
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private BigDecimal profitRate;
    private BigDecimal profit;
    private BigDecimal fee;
    private Integer status; // 0-进行中，1-盈利，2-亏损，3-平局
    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

