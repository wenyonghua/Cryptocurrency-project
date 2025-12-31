package com.crypto.platform.admin.service;

import com.crypto.platform.admin.dto.PlayerDetailDTO;

/**
 * 玩家管理服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
public interface IPlayerManagementService {
    
    /**
     * 获取玩家详情
     * 
     * @param userId 用户ID
     * @return 玩家详情
     */
    PlayerDetailDTO getPlayerDetail(Long userId);
    
    /**
     * 禁用/启用玩家
     * 
     * @param userId 用户ID
     * @param status 状态：0-禁用，1-正常
     * @return 是否成功
     */
    boolean updatePlayerStatus(Long userId, Integer status);
    
    /**
     * 设置玩家VIP等级
     * 
     * @param userId 用户ID
     * @param vipLevel VIP等级
     * @return 是否成功
     */
    boolean updatePlayerVipLevel(Long userId, Integer vipLevel);
}

