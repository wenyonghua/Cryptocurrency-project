package com.crypto.platform.deposit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.asset.service.IUserAssetService;
import com.crypto.platform.deposit.entity.DepositOrder;
import com.crypto.platform.deposit.mapper.DepositOrderMapper;
import com.crypto.platform.deposit.service.IDepositOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 充值订单服务实现
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
@RequiredArgsConstructor
public class DepositOrderServiceImpl extends ServiceImpl<DepositOrderMapper, DepositOrder> implements IDepositOrderService {

    private final IUserAssetService userAssetService;

    @Override
    public Page<DepositOrder> pageQuery(Page<DepositOrder> page, DepositOrder query) {
        LambdaQueryWrapper<DepositOrder> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(query.getOrderNo())) {
            wrapper.eq(DepositOrder::getOrderNo, query.getOrderNo());
        }
        if (query.getUserId() != null) {
            wrapper.eq(DepositOrder::getUserId, query.getUserId());
        }
        if (StringUtils.hasText(query.getCurrency())) {
            wrapper.eq(DepositOrder::getCurrency, query.getCurrency());
        }
        if (query.getStatus() != null) {
            wrapper.eq(DepositOrder::getStatus, query.getStatus());
        }
        
        wrapper.orderByDesc(DepositOrder::getCreateTime);
        
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmDeposit(Long id) {
        DepositOrder order = this.getById(id);
        if (order == null || order.getStatus() != 0) {
            return false;
        }
        
        // 更新订单状态
        order.setStatus(1);
        this.updateById(order);
        
        // 增加用户资产
        userAssetService.addBalance(order.getUserId(), order.getCurrency(), 1, order.getAmount());
        
        return true;
    }
}

