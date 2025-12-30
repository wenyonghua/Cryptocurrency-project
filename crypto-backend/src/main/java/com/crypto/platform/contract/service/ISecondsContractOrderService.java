package com.crypto.platform.contract.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.contract.entity.SecondsContractOrder;

public interface ISecondsContractOrderService extends IService<SecondsContractOrder> {
    Page<SecondsContractOrder> pageQuery(Page<SecondsContractOrder> page, SecondsContractOrder query);
}

