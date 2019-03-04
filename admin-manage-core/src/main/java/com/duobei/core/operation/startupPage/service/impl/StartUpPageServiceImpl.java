package com.duobei.core.operation.startupPage.service.impl;

import com.duobei.core.operation.startupPage.dao.StartUpPageDao;
import com.duobei.core.operation.startupPage.dao.mapper.StartupPageMapper;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartUpPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
@Service("startUpPageService")
public class StartUpPageServiceImpl implements StartUpPageService {
    @Resource
    StartupPageMapper startupPageMapper;

    @Resource
    StartUpPageDao startUpPageDao;

    @Override
    public StartupPage getByAppId(Integer appId) {
        return startUpPageDao.getByAppId(appId);
    }
}
