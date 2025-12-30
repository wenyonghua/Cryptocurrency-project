package com.crypto.platform.deposit.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 充值订单实体
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("deposit_order")
public class DepositOrder {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 币种
     */
    private String currency;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 来源地址
     */
    private String fromAddress;

    /**
     * 充值地址
     */
    private String toAddress;

    /**
     * 交易哈希
     */
    private String txHash;

    /**
     * 确认数
     */
    private Integer confirmations;

    /**
     * 需要确认数
     */
    private Integer requiredConfirmations;

    /**
     * 状态：0-待确认，1-成功，2-失败
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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

