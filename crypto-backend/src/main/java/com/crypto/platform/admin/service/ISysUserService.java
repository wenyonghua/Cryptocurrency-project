package com.crypto.platform.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.admin.entity.SysUser;

/**
 * 系统用户服务接口
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
public interface ISysUserService extends IService<SysUser> {
    
    /**
     * 根据用户名获取用户
     */
    SysUser getUserByUsername(String username);
    
    /**
     * 根据邮箱获取用户
     */
    SysUser getUserByEmail(String email);
    
    /**
     * 根据手机号获取用户
     */
    SysUser getUserByPhone(String phone);
}

