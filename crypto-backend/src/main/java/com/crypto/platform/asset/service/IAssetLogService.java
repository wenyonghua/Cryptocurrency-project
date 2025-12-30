package com.crypto.platform.asset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.asset.entity.AssetLog;

/**
 * 资产变动记录服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface IAssetLogService extends IService<AssetLog> {
    
    /**
     * 分页查询用户资产变动记录
     */
    Page<AssetLog> getLogsByUserId(Long userId, Integer page, Integer size);
    
    /**
     * 记录资产变动
     */
    boolean logAssetChange(AssetLog assetLog);
}
