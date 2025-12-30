package com.crypto.platform.deposit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.deposit.entity.DepositOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充值订单 Mapper
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface DepositOrderMapper extends BaseMapper<DepositOrder> {
}

