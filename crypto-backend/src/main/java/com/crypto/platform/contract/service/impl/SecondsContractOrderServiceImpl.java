package com.crypto.platform.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.contract.entity.SecondsContractOrder;
import com.crypto.platform.contract.mapper.SecondsContractOrderMapper;
import com.crypto.platform.contract.service.ISecondsContractOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SecondsContractOrderServiceImpl extends ServiceImpl<SecondsContractOrderMapper, SecondsContractOrder> implements ISecondsContractOrderService {

    @Override
    public Page<SecondsContractOrder> pageQuery(Page<SecondsContractOrder> page, SecondsContractOrder query) {
        LambdaQueryWrapper<SecondsContractOrder> wrapper = new LambdaQueryWrapper<>();
        
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.eq(SecondsContractOrder::getOrderNo, query.getOrderNo());
            }
            if (query.getUserId() != null) {
                wrapper.eq(SecondsContractOrder::getUserId, query.getUserId());
            }
            if (StringUtils.hasText(query.getSymbol())) {
                wrapper.eq(SecondsContractOrder::getSymbol, query.getSymbol());
            }
            if (query.getStatus() != null) {
                wrapper.eq(SecondsContractOrder::getStatus, query.getStatus());
            }
        }
        
        wrapper.orderByDesc(SecondsContractOrder::getCreateTime);
        return this.page(page, wrapper);
    }
}

