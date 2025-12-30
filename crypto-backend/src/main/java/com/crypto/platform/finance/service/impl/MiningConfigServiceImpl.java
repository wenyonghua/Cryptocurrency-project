package com.crypto.platform.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.finance.entity.MiningConfig;
import com.crypto.platform.finance.mapper.MiningConfigMapper;
import com.crypto.platform.finance.service.IMiningConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MiningConfigServiceImpl extends ServiceImpl<MiningConfigMapper, MiningConfig> implements IMiningConfigService {
    @Override
    public Page<MiningConfig> pageQuery(Page<MiningConfig> page, MiningConfig query) {
        LambdaQueryWrapper<MiningConfig> wrapper = new LambdaQueryWrapper<>();
        if (query != null) {
            if (StringUtils.hasText(query.getMiningName())) wrapper.like(MiningConfig::getMiningName, query.getMiningName());
            if (StringUtils.hasText(query.getCurrency())) wrapper.eq(MiningConfig::getCurrency, query.getCurrency());
            if (query.getStatus() != null) wrapper.eq(MiningConfig::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(MiningConfig::getCreateTime);
        return this.page(page, wrapper);
    }
}

