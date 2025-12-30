package com.crypto.platform.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 Mapper 接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}

