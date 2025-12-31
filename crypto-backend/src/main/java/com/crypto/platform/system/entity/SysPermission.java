package com.crypto.platform.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统权限/菜单实体
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 父权限ID
     */
    private Long parentId;
    
    /**
     * 权限名称
     */
    private String permissionName;
    
    /**
     * 权限标识
     */
    private String permissionKey;
    
    /**
     * 权限类型：1-菜单，2-按钮
     */
    private Integer permissionType;
    
    /**
     * 路由地址
     */
    private String path;
    
    /**
     * 组件路径
     */
    private String component;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;
    
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

