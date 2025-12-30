package com.crypto.platform.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.trade.entity.SpotOrder;
import com.crypto.platform.trade.mapper.SpotOrderMapper;
import com.crypto.platform.trade.service.ISpotOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 现货交易订单服务实现
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
public class SpotOrderServiceImpl extends ServiceImpl<SpotOrderMapper, SpotOrder> implements ISpotOrderService {

    @Override
    public Page<SpotOrder> pageQuery(Page<SpotOrder> page, SpotOrder query) {
        LambdaQueryWrapper<SpotOrder> wrapper = new LambdaQueryWrapper<>();
        
        if (query != null) {
            // 订单号
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.eq(SpotOrder::getOrderNo, query.getOrderNo());
            }
            // 用户ID
            if (query.getUserId() != null) {
                wrapper.eq(SpotOrder::getUserId, query.getUserId());
            }
            // 交易对
            if (StringUtils.hasText(query.getSymbol())) {
                wrapper.eq(SpotOrder::getSymbol, query.getSymbol());
            }
            // 方向
            if (query.getSide() != null) {
                wrapper.eq(SpotOrder::getSide, query.getSide());
            }
            // 状态
            if (query.getStatus() != null) {
                wrapper.eq(SpotOrder::getStatus, query.getStatus());
            }
        }
        
        wrapper.orderByDesc(SpotOrder::getCreateTime);
        return this.page(page, wrapper);
    }

    @Override
    public boolean cancelOrder(String orderNo) {
        SpotOrder order = this.getOne(new LambdaQueryWrapper<SpotOrder>()
                .eq(SpotOrder::getOrderNo, orderNo));
        
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        
        if (order.getStatus() == 2 || order.getStatus() == 3) {
            throw new RuntimeException("订单已完成或已撤销");
        }
        
        order.setStatus(3); // 已撤销
        return this.updateById(order);
    }
}

