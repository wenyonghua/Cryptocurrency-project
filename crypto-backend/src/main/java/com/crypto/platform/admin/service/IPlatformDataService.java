package com.crypto.platform.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.DailyDataDTO;
import com.crypto.platform.admin.dto.PlatformOverviewDTO;
import com.crypto.platform.admin.dto.PlayerDataDTO;

import java.time.LocalDate;

/**
 * 平台数据服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
public interface IPlatformDataService {
    
    /**
     * 获取平台概要信息
     * 
     * @return 平台概要信息
     */
    PlatformOverviewDTO getOverview();
    
    /**
     * 获取每日数据列表（分页）
     * 
     * @param current 当前页
     * @param size 每页大小
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日数据分页列表
     */
    Page<DailyDataDTO> getDailyDataList(Long current, Long size, LocalDate startDate, LocalDate endDate);
    
    /**
     * 获取玩家数据列表（分页）
     * 
     * @param current 当前页
     * @param size 每页大小
     * @param keyword 搜索关键词（用户名、邮箱、手机号）
     * @param vipLevel VIP等级筛选
     * @param status 状态筛选
     * @return 玩家数据分页列表
     */
    Page<PlayerDataDTO> getPlayerDataList(Long current, Long size, String keyword, Integer vipLevel, Integer status);
}

