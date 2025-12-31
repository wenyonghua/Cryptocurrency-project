package com.crypto.platform.admin.controller;

import com.crypto.platform.admin.dto.PlayerDetailDTO;
import com.crypto.platform.admin.service.IPlayerManagementService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理 - 玩家管理控制器
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Tag(name = "后台管理 - 玩家管理", description = "玩家管理相关接口")
@RestController
@RequestMapping("/admin/player")
@RequiredArgsConstructor
public class PlayerManagementController {
    
    private final IPlayerManagementService playerManagementService;
    
    @Operation(summary = "获取玩家详情", description = "根据用户ID获取玩家详细信息，包括资产和统计数据")
    @GetMapping("/{userId}")
    public Result<PlayerDetailDTO> getPlayerDetail(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId
    ) {
        PlayerDetailDTO detail = playerManagementService.getPlayerDetail(userId);
        return Result.success(detail);
    }
    
    @Operation(summary = "更新玩家状态", description = "禁用或启用玩家账号")
    @PutMapping("/{userId}/status")
    public Result<Boolean> updatePlayerStatus(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,
            
            @Parameter(description = "状态：0-禁用，1-正常", example = "1")
            @RequestParam Integer status
    ) {
        boolean success = playerManagementService.updatePlayerStatus(userId, status);
        return Result.success(success);
    }
    
    @Operation(summary = "设置玩家VIP等级", description = "修改玩家的VIP等级")
    @PutMapping("/{userId}/vip-level")
    public Result<Boolean> updatePlayerVipLevel(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,
            
            @Parameter(description = "VIP等级", example = "1")
            @RequestParam Integer vipLevel
    ) {
        boolean success = playerManagementService.updatePlayerVipLevel(userId, vipLevel);
        return Result.success(success);
    }
}

