package com.crypto.platform.system.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树形结构 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class MenuTreeDTO {
    
    /**
     * 菜单ID
     */
    private Long id;
    
    /**
     * 父菜单ID
     */
    private Long parentId;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 权限标识
     */
    private String key;
    
    /**
     * 菜单类型：1-菜单，2-按钮
     */
    private Integer type;
    
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
     * 子菜单列表
     */
    private List<MenuTreeDTO> children = new ArrayList<>();
}

