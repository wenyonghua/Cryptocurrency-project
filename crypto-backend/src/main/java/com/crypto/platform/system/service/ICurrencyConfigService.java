package com.crypto.platform.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.system.entity.CurrencyConfig;

public interface ICurrencyConfigService extends IService<CurrencyConfig> {
    Page<CurrencyConfig> pageQuery(Page<CurrencyConfig> page, CurrencyConfig query);
}
