package com.crypto.platform.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.asset.entity.AssetLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产变动记录 Mapper 接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface AssetLogMapper extends BaseMapper<AssetLog> {
}
