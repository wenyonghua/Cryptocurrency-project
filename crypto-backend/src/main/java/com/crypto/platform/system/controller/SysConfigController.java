package com.crypto.platform.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.system.entity.SysConfig;
import com.crypto.platform.system.service.ISysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置控制器
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "系统配置", description = "系统配置管理接口")
@RestController
@RequestMapping("/system/config")
@RequiredArgsConstructor
public class SysConfigController {

    private final ISysConfigService configService;

    @Operation(summary = "配置列表", description = "分页查询配置列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Page<SysConfig>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<SysConfig> configPage = configService.page(new Page<>(page, size));
        return Result.success(configPage);
    }

    @Operation(summary = "获取配置", description = "根据配置键获取配置")
    @GetMapping("/{configKey}")
    public Result<SysConfig> getByKey(@PathVariable String configKey) {
        SysConfig config = configService.getConfigByKey(configKey);
        if (config == null) {
            return Result.error("配置不存在");
        }
        return Result.success(config);
    }

    @Operation(summary = "更新配置", description = "更新配置信息")
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> update(@RequestBody SysConfig config) {
        boolean success = configService.updateById(config);
        if (success) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    @Operation(summary = "新增配置", description = "新增配置")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> add(@RequestBody SysConfig config) {
        boolean success = configService.save(config);
        if (success) {
            return Result.success();
        }
        return Result.error("新增失败");
    }

    @Operation(summary = "删除配置", description = "根据ID删除配置")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = configService.removeById(id);
        if (success) {
            return Result.success();
        }
        return Result.error("删除失败");
    }
}
