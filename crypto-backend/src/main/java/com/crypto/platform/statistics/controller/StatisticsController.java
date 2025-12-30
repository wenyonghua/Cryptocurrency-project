package com.crypto.platform.statistics.controller;

import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计数据接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "统计数据")
@RestController
@RequestMapping("/admin/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class StatisticsController {

    @Operation(summary = "获取平台统计数据")
    @GetMapping("/platform")
    public Result<Map<String, Object>> getPlatformStatistics() {
        Map<String, Object> data = new HashMap<>();
        
        // 模拟数据
        data.put("totalProfit", "1,019,800");
        data.put("totalUsers", 19);
        data.put("activeUsers", 15);
        data.put("totalDeposit", "1,020,000");
        data.put("totalWithdraw", "200");
        data.put("totalTradeVolume", "5,000,000");
        
        return Result.success(data);
    }

    @Operation(summary = "获取今日统计数据")
    @GetMapping("/today")
    public Result<Map<String, Object>> getTodayStatistics() {
        Map<String, Object> data = new HashMap<>();
        
        data.put("newUsers", 2);
        data.put("activeUsers", 8);
        data.put("depositAmount", "50,000");
        data.put("withdrawAmount", "10,000");
        data.put("tradeVolume", "200,000");
        data.put("profit", "5,000");
        
        return Result.success(data);
    }
}

