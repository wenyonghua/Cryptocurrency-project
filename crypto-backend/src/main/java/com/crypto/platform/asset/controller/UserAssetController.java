package com.crypto.platform.asset.controller;

import com.crypto.platform.asset.entity.UserAsset;
import com.crypto.platform.asset.service.IUserAssetService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户资产控制器
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "资产管理", description = "用户资产管理接口")
@RestController
@RequestMapping("/asset")
@RequiredArgsConstructor
public class UserAssetController {

    private final IUserAssetService assetService;

    @Operation(summary = "获取当前用户资产列表", description = "获取当前登录用户的所有资产")
    @GetMapping("/list")
    public Result<List<UserAsset>> getMyAssets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Result.error("未登录");
        }
        
        // TODO: 从用户信息中获取userId，这里暂时返回空列表
        // Long userId = getCurrentUserId();
        // List<UserAsset> assets = assetService.getAssetsByUserId(userId);
        
        return Result.success(List.of());
    }

    @Operation(summary = "获取指定币种资产", description = "获取当前用户指定币种的资产信息")
    @GetMapping("/{currency}")
    public Result<UserAsset> getAssetByCurrency(@PathVariable String currency) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Result.error("未登录");
        }
        
        // TODO: 从用户信息中获取userId
        // Long userId = getCurrentUserId();
        // UserAsset asset = assetService.getAssetByUserIdAndCurrency(userId, currency);
        
        return Result.success(new UserAsset());
    }
}
