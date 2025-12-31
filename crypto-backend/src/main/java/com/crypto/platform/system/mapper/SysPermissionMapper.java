package com.crypto.platform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crypto.platform.system.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统权限/菜单 Mapper 接口
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
}

