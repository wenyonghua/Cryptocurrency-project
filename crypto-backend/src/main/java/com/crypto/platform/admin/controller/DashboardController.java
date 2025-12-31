package com.crypto.platform.admin.controller;

import com.crypto.platform.admin.dto.DashboardStatisticsDTO;
import com.crypto.platform.admin.service.IPlatformStatisticsService;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理 - 首页仪表盘控制器
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Tag(name = "后台管理 - 首页仪表盘", description = "首页数据统计相关接口")
@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    
    private final IPlatformStatisticsService platformStatisticsService;
    
    @Operation(summary = "获取首页统计数据", description = "获取平台总收入、玩家数量、充值/提现金额及趋势图表数据")
    @GetMapping("/statistics")
    public Result<DashboardStatisticsDTO> getStatistics() {
        DashboardStatisticsDTO statistics = platformStatisticsService.getDashboardStatistics();
        return Result.success(statistics);
    }
}

