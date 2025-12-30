package com.crypto.platform.asset.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户资产实体
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("user_asset")
public class UserAsset implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 资产ID
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
     * 可用余额
     */
    private BigDecimal available;
    
    /**
     * 冻结余额
     */
    private BigDecimal frozen;
    
    /**
     * 总余额（计算字段）
     */
    @TableField(exist = false)
    private BigDecimal total;
    
    /**
     * 逻辑删除：0-未删除，1-已删除
     */
    @TableLogic
    private Integer deleted;
    
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
    
    /**
     * 获取总余额
     */
    public BigDecimal getTotal() {
        if (available == null) {
            available = BigDecimal.ZERO;
        }
        if (frozen == null) {
            frozen = BigDecimal.ZERO;
        }
        return available.add(frozen);
    }
}
