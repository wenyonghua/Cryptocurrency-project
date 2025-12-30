package com.crypto.platform.finance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.finance.entity.MiningConfig;

public interface IMiningConfigService extends IService<MiningConfig> {
    Page<MiningConfig> pageQuery(Page<MiningConfig> page, MiningConfig query);
}

