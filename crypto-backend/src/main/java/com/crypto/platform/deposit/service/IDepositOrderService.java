package com.crypto.platform.deposit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.deposit.entity.DepositOrder;

/**
 * 充值订单服务接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface IDepositOrderService extends IService<DepositOrder> {
    
    /**
     * 分页查询充值订单
     */
    Page<DepositOrder> pageQuery(Page<DepositOrder> page, DepositOrder query);
    
    /**
     * 确认充值
     */
    boolean confirmDeposit(Long id);
}

