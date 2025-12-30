package com.crypto.platform.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.contract.entity.CycleContractOrder;
import com.crypto.platform.contract.mapper.CycleContractOrderMapper;
import com.crypto.platform.contract.service.ICycleContractOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CycleContractOrderServiceImpl extends ServiceImpl<CycleContractOrderMapper, CycleContractOrder> implements ICycleContractOrderService {

    @Override
    public Page<CycleContractOrder> pageQuery(Page<CycleContractOrder> page, CycleContractOrder query) {
        LambdaQueryWrapper<CycleContractOrder> wrapper = new LambdaQueryWrapper<>();
        
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.eq(CycleContractOrder::getOrderNo, query.getOrderNo());
            }
            if (query.getUserId() != null) {
                wrapper.eq(CycleContractOrder::getUserId, query.getUserId());
            }
            if (StringUtils.hasText(query.getSymbol())) {
                wrapper.eq(CycleContractOrder::getSymbol, query.getSymbol());
            }
            if (query.getStatus() != null) {
                wrapper.eq(CycleContractOrder::getStatus, query.getStatus());
            }
        }
        
        wrapper.orderByDesc(CycleContractOrder::getCreateTime);
        return this.page(page, wrapper);
    }
}

