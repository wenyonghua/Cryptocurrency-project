package com.crypto.platform.finance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.finance.entity.FinanceProduct;

public interface IFinanceProductService extends IService<FinanceProduct> {
    Page<FinanceProduct> pageQuery(Page<FinanceProduct> page, FinanceProduct query);
}

