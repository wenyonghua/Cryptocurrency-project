package com.crypto.platform.withdraw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.withdraw.entity.WithdrawOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提现订单 Mapper
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface WithdrawOrderMapper extends BaseMapper<WithdrawOrder> {
}

