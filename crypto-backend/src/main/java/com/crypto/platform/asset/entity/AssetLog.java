package com.crypto.platform.asset.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 资产变动记录实体
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("asset_log")
public class AssetLog implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 币种
     */
    private String currency;
    
    /**
     * 变动类型：1-充值，2-提现，3-交易，4-手续费
     */
    private Integer changeType;
    
    /**
     * 变动金额
     */
    private BigDecimal amount;
    
    /**
     * 变动前余额
     */
    private BigDecimal beforeBalance;
    
    /**
     * 变动后余额
     */
    private BigDecimal afterBalance;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
