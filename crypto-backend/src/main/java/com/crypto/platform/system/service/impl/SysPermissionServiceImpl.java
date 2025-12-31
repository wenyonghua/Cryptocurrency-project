package com.crypto.platform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.system.dto.MenuTreeDTO;
import com.crypto.platform.system.entity.SysPermission;
import com.crypto.platform.system.mapper.SysPermissionMapper;
import com.crypto.platform.system.service.ISysPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统权限/菜单服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    
    @Override
    public List<MenuTreeDTO> getMenuTree() {
        // 查询所有启用的菜单（不包括按钮）
        List<SysPermission> allMenus = this.list(
            new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getPermissionType, 1) // 只查询菜单类型
                .eq(SysPermission::getStatus, 1) // 只查询启用的
                .orderByAsc(SysPermission::getSort)
        );
        
        // 构建树形结构
        return buildMenuTree(allMenus, 0L);
    }
    
    @Override
    public List<MenuTreeDTO> getUserMenuTree(Long userId) {
        // TODO: 根据用户权限过滤菜单
        // 目前先返回所有菜单，后续可以根据用户角色权限进行过滤
        return getMenuTree();
    }
    
    /**
     * 构建菜单树
     * 
     * @param allMenus 所有菜单列表
     * @param parentId 父菜单ID
     * @return 菜单树列表
     */
    private List<MenuTreeDTO> buildMenuTree(List<SysPermission> allMenus, Long parentId) {
        List<MenuTreeDTO> menuTree = new ArrayList<>();
        
        for (SysPermission menu : allMenus) {
            if (menu.getParentId().equals(parentId)) {
                MenuTreeDTO menuDTO = convertToDTO(menu);
                
                // 递归查找子菜单
                List<MenuTreeDTO> children = buildMenuTree(allMenus, menu.getId());
                menuDTO.setChildren(children);
                
                menuTree.add(menuDTO);
            }
        }
        
        return menuTree;
    }
    
    /**
     * 转换为 DTO
     * 
     * @param permission 权限实体
     * @return 菜单 DTO
     */
    private MenuTreeDTO convertToDTO(SysPermission permission) {
        MenuTreeDTO dto = new MenuTreeDTO();
        dto.setId(permission.getId());
        dto.setParentId(permission.getParentId());
        dto.setName(permission.getPermissionName());
        dto.setKey(permission.getPermissionKey());
        dto.setType(permission.getPermissionType());
        dto.setPath(permission.getPath());
        dto.setComponent(permission.getComponent());
        dto.setIcon(permission.getIcon());
        dto.setSort(permission.getSort());
        dto.setStatus(permission.getStatus());
        return dto;
    }
}

