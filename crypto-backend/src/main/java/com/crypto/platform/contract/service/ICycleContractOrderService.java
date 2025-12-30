package com.crypto.platform.contract.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.contract.entity.CycleContractOrder;

public interface ICycleContractOrderService extends IService<CycleContractOrder> {
    Page<CycleContractOrder> pageQuery(Page<CycleContractOrder> page, CycleContractOrder query);
}

