package com.crypto.platform.withdraw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.asset.service.IUserAssetService;
import com.crypto.platform.withdraw.entity.WithdrawOrder;
import com.crypto.platform.withdraw.mapper.WithdrawOrderMapper;
import com.crypto.platform.withdraw.service.IWithdrawOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 提现订单服务实现
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
@RequiredArgsConstructor
public class WithdrawOrderServiceImpl extends ServiceImpl<WithdrawOrderMapper, WithdrawOrder> implements IWithdrawOrderService {

    private final IUserAssetService userAssetService;

    @Override
    public Page<WithdrawOrder> pageQuery(Page<WithdrawOrder> page, WithdrawOrder query) {
        LambdaQueryWrapper<WithdrawOrder> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(query.getOrderNo())) {
            wrapper.eq(WithdrawOrder::getOrderNo, query.getOrderNo());
        }
        if (query.getUserId() != null) {
            wrapper.eq(WithdrawOrder::getUserId, query.getUserId());
        }
        if (StringUtils.hasText(query.getCurrency())) {
            wrapper.eq(WithdrawOrder::getCurrency, query.getCurrency());
        }
        if (query.getStatus() != null) {
            wrapper.eq(WithdrawOrder::getStatus, query.getStatus());
        }
        
        wrapper.orderByDesc(WithdrawOrder::getCreateTime);
        
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean approve(Long id, Long auditorId) {
        WithdrawOrder order = this.getById(id);
        if (order == null || order.getStatus() != 0) {
            return false;
        }
        
        // 更新订单状态
        order.setStatus(1);
        order.setAuditorId(auditorId);
        order.setAuditTime(LocalDateTime.now());
        this.updateById(order);
        
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reject(Long id, Long auditorId, String reason) {
        WithdrawOrder order = this.getById(id);
        if (order == null || order.getStatus() != 0) {
            return false;
        }
        
        // 更新订单状态
        order.setStatus(2);
        order.setAuditorId(auditorId);
        order.setAuditTime(LocalDateTime.now());
        order.setRejectReason(reason);
        this.updateById(order);
        
        // 解冻用户资产
        userAssetService.unfreezeBalance(order.getUserId(), order.getCurrency(), 1, order.getAmount());
        
        return true;
    }
}

