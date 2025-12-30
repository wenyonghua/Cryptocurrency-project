package com.crypto.platform.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crypto.platform.finance.entity.IeoProject;
import com.crypto.platform.finance.mapper.IeoProjectMapper;
import com.crypto.platform.finance.service.IIeoProjectService;
import org.springframework.stereotype.Service;

@Service
public class IeoProjectServiceImpl extends ServiceImpl<IeoProjectMapper, IeoProject> implements IIeoProjectService {
    @Override
    public Page<IeoProject> pageQuery(Page<IeoProject> page, IeoProject query) {
        LambdaQueryWrapper<IeoProject> wrapper = new LambdaQueryWrapper<>();
        if (query != null && query.getStatus() != null) {
            wrapper.eq(IeoProject::getStatus, query.getStatus());
        }
        wrapper.orderByDesc(IeoProject::getCreateTime);
        return this.page(page, wrapper);
    }
}
