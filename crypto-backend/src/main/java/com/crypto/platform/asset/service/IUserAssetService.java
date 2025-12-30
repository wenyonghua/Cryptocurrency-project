package com.crypto.platform.asset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.asset.entity.UserAsset;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户资产服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface IUserAssetService extends IService<UserAsset> {
    
    /**
     * 根据用户ID获取资产列表
     */
    List<UserAsset> getAssetsByUserId(Long userId);
    
    /**
     * 根据用户ID和币种获取资产
     */
    UserAsset getAssetByUserIdAndCurrency(Long userId, String currency);
    
    /**
     * 增加可用余额
     */
    boolean addAvailable(Long userId, String currency, BigDecimal amount);
    
    /**
     * 减少可用余额
     */
    boolean reduceAvailable(Long userId, String currency, BigDecimal amount);
    
    /**
     * 冻结余额
     */
    boolean freezeBalance(Long userId, String currency, BigDecimal amount);
    
    /**
     * 解冻余额
     */
    boolean unfreezeBalance(Long userId, String currency, BigDecimal amount);
}
