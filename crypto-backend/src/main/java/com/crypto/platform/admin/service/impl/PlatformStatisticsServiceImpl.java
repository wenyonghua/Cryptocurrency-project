package com.crypto.platform.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.admin.dto.DashboardStatisticsDTO;
import com.crypto.platform.admin.entity.PlatformStatistics;
import com.crypto.platform.admin.mapper.PlatformStatisticsMapper;
import com.crypto.platform.admin.service.IPlatformStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台统计数据服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Service
public class PlatformStatisticsServiceImpl extends ServiceImpl<PlatformStatisticsMapper, PlatformStatistics> 
        implements IPlatformStatisticsService {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
    
    @Override
    public DashboardStatisticsDTO getDashboardStatistics() {
        DashboardStatisticsDTO dto = new DashboardStatisticsDTO();
        
        // 获取今天的统计数据
        PlatformStatistics todayStats = this.getOne(
            new LambdaQueryWrapper<PlatformStatistics>()
                .eq(PlatformStatistics::getStatDate, LocalDate.now())
        );
        
        if (todayStats != null) {
            dto.setTotalRevenue(todayStats.getTotalProfit());
            dto.setPlayerCount(todayStats.getTotalUsers());
            dto.setTotalDeposit(todayStats.getTotalDeposit());
            dto.setTotalWithdraw(todayStats.getTotalWithdraw());
        } else {
            // 如果没有今天的数据，返回默认值
            dto.setTotalRevenue(BigDecimal.ZERO);
            dto.setPlayerCount(0);
            dto.setTotalDeposit(BigDecimal.ZERO);
            dto.setTotalWithdraw(BigDecimal.ZERO);
        }
        
        // 获取最近7天的趋势数据
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        
        List<PlatformStatistics> recentStats = this.list(
            new LambdaQueryWrapper<PlatformStatistics>()
                .between(PlatformStatistics::getStatDate, startDate, endDate)
                .orderByAsc(PlatformStatistics::getStatDate)
        );
        
        // 构建充值趋势数据
        List<DashboardStatisticsDTO.TrendDataDTO> depositTrend = new ArrayList<>();
        List<DashboardStatisticsDTO.TrendDataDTO> withdrawTrend = new ArrayList<>();
        
        for (PlatformStatistics stats : recentStats) {
            // 充值趋势
            DashboardStatisticsDTO.TrendDataDTO depositData = new DashboardStatisticsDTO.TrendDataDTO();
            depositData.setDate(stats.getStatDate().format(DATE_FORMATTER));
            depositData.setAmount(stats.getTotalDeposit());
            depositTrend.add(depositData);
            
            // 提现趋势
            DashboardStatisticsDTO.TrendDataDTO withdrawData = new DashboardStatisticsDTO.TrendDataDTO();
            withdrawData.setDate(stats.getStatDate().format(DATE_FORMATTER));
            withdrawData.setAmount(stats.getTotalWithdraw());
            withdrawTrend.add(withdrawData);
        }
        
        dto.setDepositTrend(depositTrend);
        dto.setWithdrawTrend(withdrawTrend);
        
        return dto;
    }
}

