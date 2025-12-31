package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.DailyDataDTO;
import com.crypto.platform.admin.dto.PlatformOverviewDTO;
import com.crypto.platform.admin.dto.PlayerDataDTO;
import com.crypto.platform.admin.service.IPlatformDataService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 后台管理 - 平台数据控制器
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Tag(name = "后台管理 - 平台数据", description = "平台数据统计和查询相关接口")
@RestController
@RequestMapping("/admin/platform")
@RequiredArgsConstructor
public class PlatformDataController {
    
    private final IPlatformDataService platformDataService;
    
    @Operation(summary = "获取平台概要信息", description = "获取平台总体运营数据概览")
    @GetMapping("/overview")
    public Result<PlatformOverviewDTO> getOverview() {
        PlatformOverviewDTO overview = platformDataService.getOverview();
        return Result.success(overview);
    }
    
    @Operation(summary = "获取每日数据列表", description = "分页查询每日运营数据")
    @GetMapping("/daily-data")
    public Result<Page<DailyDataDTO>> getDailyDataList(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,
            
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,
            
            @Parameter(description = "开始日期", example = "2025-12-01")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            
            @Parameter(description = "结束日期", example = "2025-12-31")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        Page<DailyDataDTO> page = platformDataService.getDailyDataList(current, size, startDate, endDate);
        return Result.success(page);
    }
    
    @Operation(summary = "获取玩家数据列表", description = "分页查询玩家数据，支持搜索和筛选")
    @GetMapping("/player-data")
    public Result<Page<PlayerDataDTO>> getPlayerDataList(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,
            
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,
            
            @Parameter(description = "搜索关键词（用户名、邮箱、手机号、UID）", example = "user123")
            @RequestParam(required = false) String keyword,
            
            @Parameter(description = "VIP等级筛选", example = "1")
            @RequestParam(required = false) Integer vipLevel,
            
            @Parameter(description = "状态筛选（0-禁用，1-正常）", example = "1")
            @RequestParam(required = false) Integer status
    ) {
        Page<PlayerDataDTO> page = platformDataService.getPlayerDataList(current, size, keyword, vipLevel, status);
        return Result.success(page);
    }
}

