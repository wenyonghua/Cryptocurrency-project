package com.crypto.platform.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.system.entity.TradingPair;

public interface ITradingPairService extends IService<TradingPair> {
    Page<TradingPair> pageQuery(Page<TradingPair> page, TradingPair query);
}
