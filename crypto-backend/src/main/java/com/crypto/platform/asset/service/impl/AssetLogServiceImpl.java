package com.crypto.platform.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.asset.entity.AssetLog;
import com.crypto.platform.asset.mapper.AssetLogMapper;
import com.crypto.platform.asset.service.IAssetLogService;
import org.springframework.stereotype.Service;

/**
 * 资产变动记录服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
public class AssetLogServiceImpl extends ServiceImpl<AssetLogMapper, AssetLog> implements IAssetLogService {

    @Override
    public Page<AssetLog> getLogsByUserId(Long userId, Integer page, Integer size) {
        return this.page(new Page<>(page, size), 
                new LambdaQueryWrapper<AssetLog>()
                        .eq(AssetLog::getUserId, userId)
                        .orderByDesc(AssetLog::getCreateTime));
    }

    @Override
    public boolean logAssetChange(AssetLog assetLog) {
        return this.save(assetLog);
    }
}
