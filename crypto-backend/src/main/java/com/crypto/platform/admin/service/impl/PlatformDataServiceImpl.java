package com.crypto.platform.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.DailyDataDTO;
import com.crypto.platform.admin.dto.PlatformOverviewDTO;
import com.crypto.platform.admin.dto.PlayerDataDTO;
import com.crypto.platform.admin.entity.PlatformStatistics;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.mapper.SysUserMapper;
import com.crypto.platform.admin.service.IPlatformDataService;
import com.crypto.platform.admin.service.IPlatformStatisticsService;
import com.crypto.platform.asset.entity.UserAsset;
import com.crypto.platform.asset.mapper.UserAssetMapper;
import com.crypto.platform.deposit.entity.DepositOrder;
import com.crypto.platform.deposit.mapper.DepositOrderMapper;
import com.crypto.platform.withdraw.entity.WithdrawOrder;
import com.crypto.platform.withdraw.mapper.WithdrawOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 平台数据服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Service
@RequiredArgsConstructor
public class PlatformDataServiceImpl implements IPlatformDataService {
    
    private final IPlatformStatisticsService platformStatisticsService;
    private final SysUserMapper sysUserMapper;
    private final UserAssetMapper userAssetMapper;
    private final DepositOrderMapper depositOrderMapper;
    private final WithdrawOrderMapper withdrawOrderMapper;
    
    @Override
    public PlatformOverviewDTO getOverview() {
        PlatformOverviewDTO dto = new PlatformOverviewDTO();
        
        // 获取今天的统计数据
        PlatformStatistics todayStats = platformStatisticsService.getOne(
            new LambdaQueryWrapper<PlatformStatistics>()
                .eq(PlatformStatistics::getStatDate, LocalDate.now())
        );
        
        // 获取昨天的统计数据（用于计算累计值）
        PlatformStatistics yesterdayStats = platformStatisticsService.getOne(
            new LambdaQueryWrapper<PlatformStatistics>()
                .eq(PlatformStatistics::getStatDate, LocalDate.now().minusDays(1))
        );
        
        if (todayStats != null) {
            dto.setTotalUsers(todayStats.getTotalUsers());
            dto.setTodayNewUsers(todayStats.getNewUsers());
            dto.setTodayActiveUsers(todayStats.getActiveUsers());
            dto.setTotalDeposit(todayStats.getTotalDeposit());
            dto.setTotalWithdraw(todayStats.getTotalWithdraw());
            dto.setTotalTradeVolume(todayStats.getTotalTradeVolume());
            dto.setTotalProfit(todayStats.getTotalProfit());
            
            // 计算今日数据（今天 - 昨天）
            if (yesterdayStats != null) {
                dto.setTodayDeposit(todayStats.getTotalDeposit().subtract(yesterdayStats.getTotalDeposit()));
                dto.setTodayWithdraw(todayStats.getTotalWithdraw().subtract(yesterdayStats.getTotalWithdraw()));
                dto.setTodayTradeVolume(todayStats.getTotalTradeVolume().subtract(yesterdayStats.getTotalTradeVolume()));
                dto.setTodayProfit(todayStats.getTotalProfit().subtract(yesterdayStats.getTotalProfit()));
            } else {
                dto.setTodayDeposit(BigDecimal.ZERO);
                dto.setTodayWithdraw(BigDecimal.ZERO);
                dto.setTodayTradeVolume(BigDecimal.ZERO);
                dto.setTodayProfit(BigDecimal.ZERO);
            }
        } else {
            // 默认值
            dto.setTotalUsers(0);
            dto.setTodayNewUsers(0);
            dto.setTodayActiveUsers(0);
            dto.setTotalDeposit(BigDecimal.ZERO);
            dto.setTodayDeposit(BigDecimal.ZERO);
            dto.setTotalWithdraw(BigDecimal.ZERO);
            dto.setTodayWithdraw(BigDecimal.ZERO);
            dto.setTotalTradeVolume(BigDecimal.ZERO);
            dto.setTodayTradeVolume(BigDecimal.ZERO);
            dto.setTotalProfit(BigDecimal.ZERO);
            dto.setTodayProfit(BigDecimal.ZERO);
        }
        
        // 统计VIP用户数
        Long vipCount = sysUserMapper.selectCount(
            new LambdaQueryWrapper<SysUser>()
                .gt(SysUser::getVipLevel, 0)
                .eq(SysUser::getDeleted, 0)
        );
        dto.setVipUserCount(vipCount.intValue());
        
        // 统计已认证用户数
        Long verifiedCount = sysUserMapper.selectCount(
            new LambdaQueryWrapper<SysUser>()
                .gt(SysUser::getKycLevel, 0)
                .eq(SysUser::getDeleted, 0)
        );
        dto.setVerifiedUserCount(verifiedCount.intValue());
        
        return dto;
    }
    
    @Override
    public Page<DailyDataDTO> getDailyDataList(Long current, Long size, LocalDate startDate, LocalDate endDate) {
        Page<PlatformStatistics> page = new Page<>(current, size);
        
        LambdaQueryWrapper<PlatformStatistics> wrapper = new LambdaQueryWrapper<>();
        if (startDate != null) {
            wrapper.ge(PlatformStatistics::getStatDate, startDate);
        }
        if (endDate != null) {
            wrapper.le(PlatformStatistics::getStatDate, endDate);
        }
        wrapper.orderByDesc(PlatformStatistics::getStatDate);
        
        Page<PlatformStatistics> statsPage = platformStatisticsService.page(page, wrapper);
        
        // 转换为 DTO
        Page<DailyDataDTO> result = new Page<>(statsPage.getCurrent(), statsPage.getSize(), statsPage.getTotal());
        List<DailyDataDTO> records = statsPage.getRecords().stream().map(stats -> {
            DailyDataDTO dto = new DailyDataDTO();
            dto.setStatDate(stats.getStatDate());
            dto.setNewUsers(stats.getNewUsers());
            dto.setActiveUsers(stats.getActiveUsers());
            dto.setDepositAmount(stats.getTotalDeposit());
            dto.setWithdrawAmount(stats.getTotalWithdraw());
            dto.setTradeVolume(stats.getTotalTradeVolume());
            dto.setProfit(stats.getTotalProfit());
            // TODO: 从订单表统计笔数
            dto.setDepositCount(0);
            dto.setWithdrawCount(0);
            dto.setTradeCount(0);
            return dto;
        }).collect(Collectors.toList());
        
        result.setRecords(records);
        return result;
    }

    @Override
    public Page<PlayerDataDTO> getPlayerDataList(Long current, Long size, String keyword, Integer vipLevel, Integer status) {
        Page<SysUser> page = new Page<>(current, size);

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();

        // 搜索条件
        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w
                .like(SysUser::getUsername, keyword)
                .or().like(SysUser::getEmail, keyword)
                .or().like(SysUser::getPhone, keyword)
                .or().like(SysUser::getUid, keyword)
            );
        }

        // VIP等级筛选
        if (vipLevel != null) {
            wrapper.eq(SysUser::getVipLevel, vipLevel);
        }

        // 状态筛选
        if (status != null) {
            wrapper.eq(SysUser::getStatus, status);
        }

        wrapper.eq(SysUser::getDeleted, 0);
        wrapper.orderByDesc(SysUser::getCreateTime);

        Page<SysUser> userPage = sysUserMapper.selectPage(page, wrapper);

        // 转换为 DTO
        Page<PlayerDataDTO> result = new Page<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<PlayerDataDTO> records = userPage.getRecords().stream().map(user -> {
            PlayerDataDTO dto = new PlayerDataDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setNickname(user.getNickname());
            dto.setUid(user.getUid());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setVipLevel(user.getVipLevel());
            dto.setKycLevel(user.getKycLevel());
            dto.setStatus(user.getStatus());
            dto.setLastLoginTime(user.getLoginTime());
            dto.setLastLoginIp(user.getLoginIp());
            dto.setCreateTime(user.getCreateTime());

            // 计算总资产（USDT）
            List<UserAsset> assets = userAssetMapper.selectList(
                new LambdaQueryWrapper<UserAsset>()
                    .eq(UserAsset::getUserId, user.getId())
            );
            BigDecimal totalAsset = assets.stream()
                .map(UserAsset::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setTotalAsset(totalAsset);

            // 统计充值金额
            BigDecimal totalDeposit = depositOrderMapper.selectList(
                new LambdaQueryWrapper<DepositOrder>()
                    .eq(DepositOrder::getUserId, user.getId())
                    .eq(DepositOrder::getStatus, 1) // 成功状态
            ).stream()
                .map(DepositOrder::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setTotalDeposit(totalDeposit);

            // 统计提现金额
            BigDecimal totalWithdraw = withdrawOrderMapper.selectList(
                new LambdaQueryWrapper<WithdrawOrder>()
                    .eq(WithdrawOrder::getUserId, user.getId())
                    .eq(WithdrawOrder::getStatus, 4) // 成功状态
            ).stream()
                .map(WithdrawOrder::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setTotalWithdraw(totalWithdraw);

            // TODO: 统计交易量
            dto.setTotalTrade(BigDecimal.ZERO);

            // 统计邀请人数
            Long inviteCount = sysUserMapper.selectCount(
                new LambdaQueryWrapper<SysUser>()
                    .eq(SysUser::getInviterId, user.getId())
                    .eq(SysUser::getDeleted, 0)
            );
            dto.setInviteCount(inviteCount.intValue());

            return dto;
        }).collect(Collectors.toList());

        result.setRecords(records);
        return result;
    }
}
