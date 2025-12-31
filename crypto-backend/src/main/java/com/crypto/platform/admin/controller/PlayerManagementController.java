package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.AssetAdjustmentDTO;
import com.crypto.platform.admin.dto.PlayerDetailDTO;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.service.IPlayerManagementService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "玩家列表", description = "分页查询玩家列表（只包括普通用户）")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Page<SysUser>> getPlayerList(
            @Parameter(description = "页码", example = "1")
            @RequestParam(defaultValue = "1") Integer page,

            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Integer size,

            @Parameter(description = "用户名（模糊查询）")
            @RequestParam(required = false) String username,

            @Parameter(description = "手机号（模糊查询）")
            @RequestParam(required = false) String phone,

            @Parameter(description = "邮箱（模糊查询）")
            @RequestParam(required = false) String email
    ) {
        Page<SysUser> playerPage = playerManagementService.getPlayerList(page, size, username, phone, email);
        return Result.success(playerPage);
    }

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Boolean> updatePlayerVipLevel(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,

            @Parameter(description = "VIP等级", example = "1")
            @RequestParam Integer vipLevel
    ) {
        boolean success = playerManagementService.updatePlayerVipLevel(userId, vipLevel);
        return Result.success(success);
    }

    @Operation(summary = "调整玩家资产", description = "给玩家充值或扣除虚拟资产")
    @PostMapping("/adjust-asset")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Boolean> adjustPlayerAsset(@RequestBody AssetAdjustmentDTO dto) {
        boolean success = playerManagementService.adjustPlayerAsset(dto);
        return Result.success(success);
    }

    @Operation(summary = "禁止/允许充值提现", description = "禁止或允许玩家进行充值和提现操作")
    @PutMapping("/{userId}/deposit-withdraw-status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Boolean> updateDepositWithdrawStatus(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,

            @Parameter(description = "是否禁止：true-禁止，false-允许", example = "true")
            @RequestParam Boolean forbidden
    ) {
        boolean success = playerManagementService.updateDepositWithdrawStatus(userId, forbidden);
        return Result.success(success);
    }

    @Operation(summary = "获取上级代理链", description = "获取玩家的上级代理链（最多两级）")
    @GetMapping("/{userId}/agent-chain")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<List<SysUser>> getAgentChain(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId
    ) {
        List<SysUser> agentChain = playerManagementService.getAgentChain(userId);
        return Result.success(agentChain);
    }

    @Operation(summary = "更新玩家信息", description = "更新玩家的基本信息")
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Boolean> updatePlayerInfo(@RequestBody SysUser user) {
        boolean success = playerManagementService.updatePlayerInfo(user);
        return Result.success(success);
    }
}

