package com.crypto.platform.system.controller;

import com.crypto.platform.common.Result;
import com.crypto.platform.system.dto.MenuTreeDTO;
import com.crypto.platform.system.service.ISysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理控制器
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Tag(name = "菜单管理", description = "菜单管理接口")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class MenuController {
    
    private final ISysPermissionService permissionService;
    
    @Operation(summary = "获取菜单树", description = "获取所有菜单的树形结构")
    @GetMapping("/tree")
    public Result<List<MenuTreeDTO>> getMenuTree() {
        List<MenuTreeDTO> menuTree = permissionService.getMenuTree();
        return Result.success(menuTree);
    }
    
    @Operation(summary = "获取当前用户菜单树", description = "根据当前用户权限获取菜单树")
    @GetMapping("/user-tree")
    public Result<List<MenuTreeDTO>> getUserMenuTree() {
        // TODO: 从 SecurityContext 获取当前用户ID
        // 目前先返回所有菜单
        List<MenuTreeDTO> menuTree = permissionService.getMenuTree();
        return Result.success(menuTree);
    }
}

