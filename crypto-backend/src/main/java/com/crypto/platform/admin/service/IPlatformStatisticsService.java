package com.crypto.platform.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.admin.dto.DashboardStatisticsDTO;
import com.crypto.platform.admin.entity.PlatformStatistics;

/**
 * 平台统计数据服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
public interface IPlatformStatisticsService extends IService<PlatformStatistics> {
    
    /**
     * 获取首页仪表盘统计数据
     * 
     * @return 仪表盘统计数据
     */
    DashboardStatisticsDTO getDashboardStatistics();
}

