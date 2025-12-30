package com.crypto.platform.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.trade.entity.SpotOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 现货交易订单 Mapper
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface SpotOrderMapper extends BaseMapper<SpotOrder> {
}

