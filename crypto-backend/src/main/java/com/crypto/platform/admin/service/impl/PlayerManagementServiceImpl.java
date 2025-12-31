package com.crypto.platform.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.AssetAdjustmentDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        dto.setLastLoginTime(user.getLoginTime());
        dto.setLastLoginIp(user.getLoginIp());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean adjustPlayerAsset(AssetAdjustmentDTO dto) {
        // 查询用户资产
        UserAsset asset = userAssetMapper.selectOne(
            new LambdaQueryWrapper<UserAsset>()
                .eq(UserAsset::getUserId, dto.getUserId())
                .eq(UserAsset::getCurrency, dto.getCurrency())
        );

        if (asset == null) {
            // 如果资产不存在，创建新资产
            asset = new UserAsset();
            asset.setUserId(dto.getUserId());
            asset.setCurrency(dto.getCurrency());
            asset.setAvailable(BigDecimal.ZERO);
            asset.setFrozen(BigDecimal.ZERO);
            asset.setAssetType(1); // 默认为平台资产
            userAssetMapper.insert(asset);
        }

        // 调整资产
        BigDecimal newBalance;
        if (dto.getAdjustType() == 1) {
            // 充值：增加可用余额
            newBalance = asset.getAvailable().add(dto.getAmount());
        } else {
            // 扣除：减少可用余额
            newBalance = asset.getAvailable().subtract(dto.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("余额不足，无法扣除");
            }
        }

        asset.setAvailable(newBalance);
        return userAssetMapper.updateById(asset) > 0;
    }

    @Override
    public boolean updateDepositWithdrawStatus(Long userId, boolean forbidden) {
        // 使用 tags 字段存储禁止充值提现的标记
        SysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        String tags = user.getTags() == null ? "" : user.getTags();
        if (forbidden) {
            // 添加禁止标记
            if (!tags.contains("FORBIDDEN_DEPOSIT_WITHDRAW")) {
                tags = tags.isEmpty() ? "FORBIDDEN_DEPOSIT_WITHDRAW" : tags + ",FORBIDDEN_DEPOSIT_WITHDRAW";
            }
        } else {
            // 移除禁止标记
            tags = tags.replace("FORBIDDEN_DEPOSIT_WITHDRAW", "").replace(",,", ",");
            if (tags.startsWith(",")) tags = tags.substring(1);
            if (tags.endsWith(",")) tags = tags.substring(0, tags.length() - 1);
        }

        SysUser updateUser = new SysUser();
        updateUser.setId(userId);
        updateUser.setTags(tags);
        return sysUserMapper.updateById(updateUser) > 0;
    }

    @Override
    public List<SysUser> getAgentChain(Long userId) {
        List<SysUser> agentChain = new ArrayList<>();
        SysUser currentUser = sysUserMapper.selectById(userId);

        // 最多查找两级上级代理
        int maxLevel = 2;
        int currentLevel = 0;

        while (currentUser != null && currentUser.getAgentId() != null && currentLevel < maxLevel) {
            SysUser agent = sysUserMapper.selectById(currentUser.getAgentId());
            if (agent != null) {
                agentChain.add(agent);
                currentUser = agent;
                currentLevel++;
            } else {
                break;
            }
        }

        return agentChain;
    }

    @Override
    public boolean updatePlayerInfo(SysUser user) {
        return sysUserMapper.updateById(user) > 0;
    }

    @Override
    public Page<SysUser> getPlayerList(Integer page, Integer size, String username, String phone, String email) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();

        // 只查询普通用户（user_type = 0）
        wrapper.eq(SysUser::getUserType, 0);

        // 模糊查询条件
        if (StringUtils.hasText(username)) {
            wrapper.like(SysUser::getUsername, username);
        }
        if (StringUtils.hasText(phone)) {
            wrapper.like(SysUser::getPhone, phone);
        }
        if (StringUtils.hasText(email)) {
            wrapper.like(SysUser::getEmail, email);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(SysUser::getCreateTime);

        return sysUserMapper.selectPage(new Page<>(page, size), wrapper);
    }
}

