package com.crypto.platform.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.finance.entity.FinanceProduct;
import com.crypto.platform.finance.mapper.FinanceProductMapper;
import com.crypto.platform.finance.service.IFinanceProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FinanceProductServiceImpl extends ServiceImpl<FinanceProductMapper, FinanceProduct> implements IFinanceProductService {

    @Override
    public Page<FinanceProduct> pageQuery(Page<FinanceProduct> page, FinanceProduct query) {
        LambdaQueryWrapper<FinanceProduct> wrapper = new LambdaQueryWrapper<>();
        
        if (query != null) {
            if (StringUtils.hasText(query.getProductName())) {
                wrapper.like(FinanceProduct::getProductName, query.getProductName());
            }
            if (StringUtils.hasText(query.getCurrency())) {
                wrapper.eq(FinanceProduct::getCurrency, query.getCurrency());
            }
            if (query.getStatus() != null) {
                wrapper.eq(FinanceProduct::getStatus, query.getStatus());
            }
        }
        
        wrapper.orderByDesc(FinanceProduct::getCreateTime);
        return this.page(page, wrapper);
    }
}

