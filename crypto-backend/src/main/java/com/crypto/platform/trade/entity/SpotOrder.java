package com.crypto.platform.trade.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 现货交易订单实体
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("spot_order")
public class SpotOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
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
     * 交易对
     */
    private String symbol;

    /**
     * 方向：1-买入，2-卖出
     */
    private Integer side;

    /**
     * 订单类型：1-限价，2-市价
     */
    private Integer orderType;

    /**
     * 委托价格
     */
    private BigDecimal price;

    /**
     * 委托数量
     */
    private BigDecimal amount;

    /**
     * 委托总额
     */
    private BigDecimal total;

    /**
     * 成交数量
     */
    private BigDecimal filledAmount;

    /**
     * 成交总额
     */
    private BigDecimal filledTotal;

    /**
     * 成交均价
     */
    private BigDecimal avgPrice;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 手续费币种
     */
    private String feeCurrency;

    /**
     * 状态：0-待成交，1-部分成交，2-完全成交，3-已撤销，4-已过期
     */
    private Integer status;

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

