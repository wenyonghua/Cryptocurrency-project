package com.crypto.platform.withdraw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.withdraw.entity.WithdrawOrder;

/**
 * 提现订单服务接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface IWithdrawOrderService extends IService<WithdrawOrder> {
    
    /**
     * 分页查询提现订单
     */
    Page<WithdrawOrder> pageQuery(Page<WithdrawOrder> page, WithdrawOrder query);
    
    /**
     * 审核通过
     */
    boolean approve(Long id, Long auditorId);
    
    /**
     * 审核拒绝
     */
    boolean reject(Long id, Long auditorId, String reason);
}

