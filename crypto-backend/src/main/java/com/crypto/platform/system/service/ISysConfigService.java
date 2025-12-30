package com.crypto.platform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.system.entity.SysConfig;

/**
 * 系统配置服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface ISysConfigService extends IService<SysConfig> {
    
    /**
     * 根据配置键获取配置值
     */
    String getConfigValue(String configKey);
    
    /**
     * 根据配置键获取配置
     */
    SysConfig getConfigByKey(String configKey);
    
    /**
     * 更新配置
     */
    boolean updateConfig(String configKey, String configValue);
}
