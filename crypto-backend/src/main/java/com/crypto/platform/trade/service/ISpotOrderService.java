package com.crypto.platform.trade.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.trade.entity.SpotOrder;

/**
 * 现货交易订单服务接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface ISpotOrderService extends IService<SpotOrder> {

    /**
     * 分页查询订单
     */
    Page<SpotOrder> pageQuery(Page<SpotOrder> page, SpotOrder query);

    /**
     * 撤销订单
     */
    boolean cancelOrder(String orderNo);
}

