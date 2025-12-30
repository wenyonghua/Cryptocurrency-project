package com.crypto.platform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.system.entity.SysConfig;
import com.crypto.platform.system.mapper.SysConfigMapper;
import com.crypto.platform.system.service.ISysConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统配置服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Override
    public String getConfigValue(String configKey) {
        SysConfig config = getConfigByKey(configKey);
        return config != null ? config.getConfigValue() : null;
    }

    @Override
    public SysConfig getConfigByKey(String configKey) {
        return this.getOne(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getConfigKey, configKey));
    }

    @Override
    public boolean updateConfig(String configKey, String configValue) {
        return this.update(new LambdaUpdateWrapper<SysConfig>()
                .eq(SysConfig::getConfigKey, configKey)
                .set(SysConfig::getConfigValue, configValue));
    }
}
