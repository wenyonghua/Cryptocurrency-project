package com.crypto.platform.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.asset.entity.UserAsset;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户资产 Mapper 接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface UserAssetMapper extends BaseMapper<UserAsset> {
}
