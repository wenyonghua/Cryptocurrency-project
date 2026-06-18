package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.asset.entity.UserAsset;
import com.crypto.platform.asset.mapper.UserAssetMapper;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理 - 玩家资产
 */
@Tag(name = "后台管理 - 玩家资产")
@RestController
@RequestMapping("/admin/asset")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminAssetController {

    private final UserAssetMapper userAssetMapper;

    @Operation(summary = "玩家资产列表")
    @GetMapping("/list")
    public Result<Page<UserAsset>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) Integer assetType) {
        LambdaQueryWrapper<UserAsset> wrapper = new LambdaQueryWrapper<>();
        if (userId != null) {
            wrapper.eq(UserAsset::getUserId, userId);
        }
        if (StringUtils.hasText(currency)) {
            wrapper.eq(UserAsset::getCurrency, currency);
        }
        if (assetType != null) {
            wrapper.eq(UserAsset::getAssetType, assetType);
        }
        wrapper.orderByDesc(UserAsset::getUpdateTime);
        return Result.success(userAssetMapper.selectPage(new Page<>(page, size), wrapper));
    }
}
