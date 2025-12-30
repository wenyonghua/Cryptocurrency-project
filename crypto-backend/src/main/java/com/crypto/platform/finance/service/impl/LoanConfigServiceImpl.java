package com.crypto.platform.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.finance.entity.LoanConfig;
import com.crypto.platform.finance.mapper.LoanConfigMapper;
import com.crypto.platform.finance.service.ILoanConfigService;
import org.springframework.stereotype.Service;

@Service
public class LoanConfigServiceImpl extends ServiceImpl<LoanConfigMapper, LoanConfig> implements ILoanConfigService {
    @Override
    public Page<LoanConfig> pageQuery(Page<LoanConfig> page, LoanConfig query) {
        LambdaQueryWrapper<LoanConfig> wrapper = new LambdaQueryWrapper<>();
        if (query != null && query.getStatus() != null) {
            wrapper.eq(LoanConfig::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(LoanConfig::getCreateTime);
        return this.page(page, wrapper);
    }
}
