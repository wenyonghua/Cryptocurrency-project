package com.crypto.platform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.system.entity.CurrencyConfig;
import com.crypto.platform.system.mapper.CurrencyConfigMapper;
import com.crypto.platform.system.service.ICurrencyConfigService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConfigServiceImpl extends ServiceImpl<CurrencyConfigMapper, CurrencyConfig> implements ICurrencyConfigService {
    @Override
    public Page<CurrencyConfig> pageQuery(Page<CurrencyConfig> page, CurrencyConfig query) {
        LambdaQueryWrapper<CurrencyConfig> wrapper = new LambdaQueryWrapper<>();
        if (query != null && query.getStatus() != null) {
            wrapper.eq(CurrencyConfig::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(CurrencyConfig::getCreateTime);
        return this.page(page, wrapper);
    }
}
