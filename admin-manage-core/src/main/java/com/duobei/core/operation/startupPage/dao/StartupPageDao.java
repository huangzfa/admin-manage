package com.duobei.core.operation.startupPage.dao;

import com.duobei.core.operation.startupPage.domain.StartupPage;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
public interface StartupPageDao {
    StartupPage getByAppId(Integer appId);

    int updateById(StartupPage startupPage);

    int save(StartupPage startupPage);

    int updateIsEnableById(StartupPage startupPage);
}
