package com.crypto.platform.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.dto.AssetAdjustmentDTO;
import com.crypto.platform.admin.dto.PlayerDetailDTO;
import com.crypto.platform.admin.entity.SysUser;

import java.util.List;

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

    /**
     * 调整玩家资产（充值或扣除）
     *
     * @param dto 资产调整信息
     * @return 是否成功
     */
    boolean adjustPlayerAsset(AssetAdjustmentDTO dto);

    /**
     * 禁止/允许玩家充值提现
     *
     * @param userId 用户ID
     * @param forbidden 是否禁止：true-禁止，false-允许
     * @return 是否成功
     */
    boolean updateDepositWithdrawStatus(Long userId, boolean forbidden);

    /**
     * 获取玩家的上级代理链
     *
     * @param userId 用户ID
     * @return 上级代理列表（从直接上级到顶级）
     */
    List<SysUser> getAgentChain(Long userId);

    /**
     * 更新玩家信息
     *
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updatePlayerInfo(SysUser user);

    /**
     * 分页查询玩家列表（只查询普通用户，不包括管理员）
     *
     * @param page 页码
     * @param size 每页大小
     * @param username 用户名（模糊查询）
     * @param phone 手机号（模糊查询）
     * @param email 邮箱（模糊查询）
     * @return 玩家列表
     */
    Page<SysUser> getPlayerList(Integer page, Integer size, String username, String phone, String email);
}

