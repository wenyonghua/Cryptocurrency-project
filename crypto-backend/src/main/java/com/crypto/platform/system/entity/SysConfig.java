package com.crypto.platform.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置实体
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 配置键
     */
    private String configKey;
    
    /**
     * 配置值
     */
    private String configValue;
    
    /**
     * 配置类型
     */
    private String configType;
    
    /**
     * 备注
     */
    private String remark;
    
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
}
