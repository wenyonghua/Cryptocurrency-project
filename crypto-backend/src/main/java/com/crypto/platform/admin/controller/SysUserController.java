package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.service.ISysUserService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户管理控制器
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "用户管理", description = "系统用户管理接口")
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class SysUserController {

    private final ISysUserService userService;

    @Operation(summary = "用户列表", description = "分页查询用户列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Page<SysUser>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<SysUser> userPage = userService.page(new Page<>(page, size));
        return Result.success(userPage);
    }

    @Operation(summary = "用户详情", description = "根据ID获取用户详情")
    @GetMapping("/{id}")
    public Result<SysUser> getById(@PathVariable Long id) {
        SysUser user = userService.getById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @Operation(summary = "更新用户", description = "更新用户信息")
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> update(@RequestBody SysUser user) {
        boolean success = userService.updateById(user);
        if (success) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    @Operation(summary = "删除用户", description = "根据ID删除用户（逻辑删除）")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = userService.removeById(id);
        if (success) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

    @Operation(summary = "启用/禁用用户", description = "修改用户状态")
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        boolean success = userService.updateById(user);
        if (success) {
            return Result.success("状态更新成功");
        }
        return Result.error("状态更新失败");
    }
}

