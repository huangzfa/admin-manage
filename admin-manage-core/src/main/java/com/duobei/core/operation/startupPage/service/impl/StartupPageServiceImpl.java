package com.duobei.core.operation.startupPage.service.impl;

import com.duobei.core.operation.startupPage.dao.StartupPageDao;
import com.duobei.core.operation.startupPage.dao.mapper.StartupPageMapper;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartupPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
@Service("startUpPageService")
public class StartupPageServiceImpl implements StartupPageService {
    @Resource
    StartupPageMapper startupPageMapper;

    @Resource
    StartupPageDao startUpPageDao;

    @Override
    public StartupPage getByAppId(Integer appId) {
        return startUpPageDao.getByAppId(appId);
    }

    @Override
    public void updateById(StartupPage startupPage) {
      int count = startUpPageDao.updateById(startupPage);
      if (count != 1){
          throw new RuntimeException("修改启动页配置失败");
      }
    }

    @Override
    public void save(StartupPage startupPage) {
        int count = startUpPageDao.save(startupPage);
        if (count != 1){
            throw new RuntimeException("新增启动页配置失败");
        }
    }

    @Override
    public void updateIsEnableById(StartupPage startupPage) {
        int count = startUpPageDao.updateIsEnableById(startupPage);
        if (count != 1){
            throw new RuntimeException("新增启动页配置失败");
        }
    }
}
