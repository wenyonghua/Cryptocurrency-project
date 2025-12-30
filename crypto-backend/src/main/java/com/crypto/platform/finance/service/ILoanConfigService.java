package com.crypto.platform.finance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.finance.entity.LoanConfig;

public interface ILoanConfigService extends IService<LoanConfig> {
    Page<LoanConfig> pageQuery(Page<LoanConfig> page, LoanConfig query);
}
