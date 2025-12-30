package com.crypto.platform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.system.entity.TradingPair;
import com.crypto.platform.system.mapper.TradingPairMapper;
import com.crypto.platform.system.service.ITradingPairService;
import org.springframework.stereotype.Service;

@Service
public class TradingPairServiceImpl extends ServiceImpl<TradingPairMapper, TradingPair> implements ITradingPairService {
    @Override
    public Page<TradingPair> pageQuery(Page<TradingPair> page, TradingPair query) {
        LambdaQueryWrapper<TradingPair> wrapper = new LambdaQueryWrapper<>();
        if (query != null && query.getStatus() != null) {
            wrapper.eq(TradingPair::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(TradingPair::getCreateTime);
        return this.page(page, wrapper);
    }
}
