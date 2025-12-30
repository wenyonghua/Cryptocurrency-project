package com.crypto.platform.finance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crypto.platform.finance.entity.IeoProject;

public interface IIeoProjectService extends IService<IeoProject> {
    Page<IeoProject> pageQuery(Page<IeoProject> page, IeoProject query);
}
