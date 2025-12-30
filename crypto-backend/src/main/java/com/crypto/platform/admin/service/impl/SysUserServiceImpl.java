package com.crypto.platform.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.mapper.SysUserMapper;
import com.crypto.platform.admin.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务实现
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getUserByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
    }

    @Override
    public SysUser getUserByEmail(String email) {
        return this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getEmail, email));
    }

    @Override
    public SysUser getUserByPhone(String phone) {
        return this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getPhone, phone));
    }
}

