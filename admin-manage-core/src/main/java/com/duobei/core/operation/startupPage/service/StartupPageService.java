package com.duobei.core.operation.startupPage.service;

import com.duobei.core.operation.startupPage.domain.StartupPage;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
public interface StartupPageService {
    /**
     * 根据appId获取
     * @param appId
     * @return
     */
    StartupPage getByAppId(Integer appId);

    /**
     * 根据id修改启动页
     * @param startupPage
     */
    void updateById(StartupPage startupPage);

    /**
     * 新增
     * @param startupPage
     */
    void save(StartupPage startupPage);
}
