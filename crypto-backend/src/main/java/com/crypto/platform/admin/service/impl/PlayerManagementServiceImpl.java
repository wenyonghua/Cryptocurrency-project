package com.crypto.platform.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crypto.platform.admin.dto.PlayerDetailDTO;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.mapper.SysUserMapper;
import com.crypto.platform.admin.service.IPlayerManagementService;
import com.crypto.platform.asset.entity.UserAsset;
import com.crypto.platform.asset.mapper.UserAssetMapper;
import com.crypto.platform.deposit.entity.DepositOrder;
import com.crypto.platform.deposit.mapper.DepositOrderMapper;
import com.crypto.platform.withdraw.entity.WithdrawOrder;
import com.crypto.platform.withdraw.mapper.WithdrawOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 玩家管理服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Service
@RequiredArgsConstructor
public class PlayerManagementServiceImpl implements IPlayerManagementService {
    
    private final SysUserMapper sysUserMapper;
    private final UserAssetMapper userAssetMapper;
    private final DepositOrderMapper depositOrderMapper;
    private final WithdrawOrderMapper withdrawOrderMapper;
    
    @Override
    public PlayerDetailDTO getPlayerDetail(Long userId) {
        // 获取用户信息
        SysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        PlayerDetailDTO dto = new PlayerDetailDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNickname(user.getNickname());
        dto.setUid(user.getUid());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAvatar(user.getAvatar());
        dto.setVipLevel(user.getVipLevel());
        dto.setKycLevel(user.getKycLevel());
        dto.setStatus(user.getStatus());
        dto.setRealName(user.getRealName());
        dto.setInviteCode(user.getInviteCode());
        dto.setInviterId(user.getInviterId());
        dto.setLastLoginTime(user.getLastLoginTime());
        dto.setLastLoginIp(user.getLastLoginIp());
        dto.setCreateTime(user.getCreateTime());
        
        // 获取邀请人信息
        if (user.getInviterId() != null) {
            SysUser inviter = sysUserMapper.selectById(user.getInviterId());
            if (inviter != null) {
                dto.setInviterUsername(inviter.getUsername());
            }
        }
        
        // 获取资产列表
        List<UserAsset> assets = userAssetMapper.selectList(
            new LambdaQueryWrapper<UserAsset>()
                .eq(UserAsset::getUserId, userId)
        );
        List<PlayerDetailDTO.AssetDTO> assetDTOs = assets.stream().map(asset -> {
            PlayerDetailDTO.AssetDTO assetDTO = new PlayerDetailDTO.AssetDTO();
            assetDTO.setCurrency(asset.getCurrency());
            assetDTO.setAvailable(asset.getAvailable());
            assetDTO.setFrozen(asset.getFrozen());
            assetDTO.setTotal(asset.getTotal());
            return assetDTO;
        }).collect(Collectors.toList());
        dto.setAssets(assetDTOs);
        
        // 统计数据
        PlayerDetailDTO.StatisticsDTO statistics = new PlayerDetailDTO.StatisticsDTO();
        
        // 累计充值
        BigDecimal totalDeposit = depositOrderMapper.selectList(
            new LambdaQueryWrapper<DepositOrder>()
                .eq(DepositOrder::getUserId, userId)
                .eq(DepositOrder::getStatus, 1)
        ).stream()
            .map(DepositOrder::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        statistics.setTotalDeposit(totalDeposit);
        
        // 累计提现
        BigDecimal totalWithdraw = withdrawOrderMapper.selectList(
            new LambdaQueryWrapper<WithdrawOrder>()
                .eq(WithdrawOrder::getUserId, userId)
                .eq(WithdrawOrder::getStatus, 4)
        ).stream()
            .map(WithdrawOrder::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        statistics.setTotalWithdraw(totalWithdraw);
        
        // TODO: 累计交易量
        statistics.setTotalTrade(BigDecimal.ZERO);
        
        // 邀请人数
        Long inviteCount = sysUserMapper.selectCount(
            new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getInviterId, userId)
                .eq(SysUser::getDeleted, 0)
        );
        statistics.setInviteCount(inviteCount.intValue());
        
        dto.setStatistics(statistics);
        
        return dto;
    }
    
    @Override
    public boolean updatePlayerStatus(Long userId, Integer status) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setStatus(status);
        return sysUserMapper.updateById(user) > 0;
    }
    
    @Override
    public boolean updatePlayerVipLevel(Long userId, Integer vipLevel) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setVipLevel(vipLevel);
        return sysUserMapper.updateById(user) > 0;
    }
}

