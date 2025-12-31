package com.crypto.platform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.system.dto.MenuTreeDTO;
import com.crypto.platform.system.entity.SysPermission;

import java.util.List;

/**
 * 系统权限/菜单服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
public interface ISysPermissionService extends IService<SysPermission> {
    
    /**
     * 获取菜单树
     * 
     * @return 菜单树列表
     */
    List<MenuTreeDTO> getMenuTree();
    
    /**
     * 获取用户菜单树（根据用户权限过滤）
     * 
     * @param userId 用户ID
     * @return 菜单树列表
     */
    List<MenuTreeDTO> getUserMenuTree(Long userId);
}

