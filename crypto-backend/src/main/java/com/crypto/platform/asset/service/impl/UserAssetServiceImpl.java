package com.crypto.platform.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.asset.entity.UserAsset;
import com.crypto.platform.asset.mapper.UserAssetMapper;
import com.crypto.platform.asset.service.IUserAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户资产服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Slf4j
@Service
public class UserAssetServiceImpl extends ServiceImpl<UserAssetMapper, UserAsset> implements IUserAssetService {

    @Override
    public List<UserAsset> getAssetsByUserId(Long userId) {
        return this.list(new LambdaQueryWrapper<UserAsset>()
                .eq(UserAsset::getUserId, userId)
                .orderByDesc(UserAsset::getCreateTime));
    }

    @Override
    public UserAsset getAssetByUserIdAndCurrency(Long userId, String currency) {
        UserAsset asset = this.getOne(new LambdaQueryWrapper<UserAsset>()
                .eq(UserAsset::getUserId, userId)
                .eq(UserAsset::getCurrency, currency));
        
        // 如果资产不存在，创建一个新的
        if (asset == null) {
            asset = new UserAsset();
            asset.setUserId(userId);
            asset.setCurrency(currency);
            asset.setAvailable(BigDecimal.ZERO);
            asset.setFrozen(BigDecimal.ZERO);
            this.save(asset);
        }
        
        return asset;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAvailable(Long userId, String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("增加金额必须大于0");
            return false;
        }
        
        UserAsset asset = getAssetByUserIdAndCurrency(userId, currency);
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available + " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reduceAvailable(Long userId, String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("减少金额必须大于0");
            return false;
        }
        
        UserAsset asset = getAssetByUserIdAndCurrency(userId, currency);
        
        if (asset.getAvailable().compareTo(amount) < 0) {
            log.error("可用余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available - " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean freezeBalance(Long userId, String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("冻结金额必须大于0");
            return false;
        }
        
        UserAsset asset = getAssetByUserIdAndCurrency(userId, currency);
        
        if (asset.getAvailable().compareTo(amount) < 0) {
            log.error("可用余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available - " + amount + ", frozen = frozen + " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unfreezeBalance(Long userId, String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("解冻金额必须大于0");
            return false;
        }
        
        UserAsset asset = getAssetByUserIdAndCurrency(userId, currency);
        
        if (asset.getFrozen().compareTo(amount) < 0) {
            log.error("冻结余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("frozen = frozen - " + amount + ", available = available + " + amount));
    }
}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBalance(Long userId, String currency, Integer assetType, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("增加金额必须大于0");
            return false;
        }
        
        UserAsset asset = getOrCreateAsset(userId, currency, assetType);
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available + " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean subtractBalance(Long userId, String currency, Integer assetType, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("减少金额必须大于0");
            return false;
        }
        
        UserAsset asset = getOrCreateAsset(userId, currency, assetType);
        
        if (asset.getAvailable().compareTo(amount) < 0) {
            log.error("可用余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available - " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean freezeBalance(Long userId, String currency, Integer assetType, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("冻结金额必须大于0");
            return false;
        }
        
        UserAsset asset = getOrCreateAsset(userId, currency, assetType);
        
        if (asset.getAvailable().compareTo(amount) < 0) {
            log.error("可用余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("available = available - " + amount + ", frozen = frozen + " + amount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unfreezeBalance(Long userId, String currency, Integer assetType, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("解冻金额必须大于0");
            return false;
        }
        
        UserAsset asset = getOrCreateAsset(userId, currency, assetType);
        
        if (asset.getFrozen().compareTo(amount) < 0) {
            log.error("冻结余额不足");
            return false;
        }
        
        return this.update(new LambdaUpdateWrapper<UserAsset>()
                .eq(UserAsset::getId, asset.getId())
                .setSql("frozen = frozen - " + amount + ", available = available + " + amount));
    }
    
    /**
     * 获取或创建资产
     */
    private UserAsset getOrCreateAsset(Long userId, String currency, Integer assetType) {
        UserAsset asset = this.getOne(new LambdaQueryWrapper<UserAsset>()
                .eq(UserAsset::getUserId, userId)
                .eq(UserAsset::getCurrency, currency)
                .eq(UserAsset::getAssetType, assetType));
        
        if (asset == null) {
            asset = new UserAsset();
            asset.setUserId(userId);
            asset.setCurrency(currency);
            asset.setAssetType(assetType);
            asset.setAvailable(BigDecimal.ZERO);
            asset.setFrozen(BigDecimal.ZERO);
            this.save(asset);
        }
        
        return asset;
    }
}
