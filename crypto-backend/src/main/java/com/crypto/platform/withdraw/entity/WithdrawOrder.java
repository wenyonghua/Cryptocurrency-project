package com.crypto.platform.withdraw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 提现订单实体
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("withdraw_order")
public class WithdrawOrder {

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
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 实际到账金额
     */
    private BigDecimal actualAmount;

    /**
     * 提现地址
     */
    private String toAddress;

    /**
     * 交易哈希
     */
    private String txHash;

    /**
     * 状态：0-待审核，1-审核通过，2-审核拒绝，3-处理中，4-成功，5-失败
     */
    private Integer status;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核人ID
     */
    private Long auditorId;

    /**
     * 拒绝原因
     */
    private String rejectReason;

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

