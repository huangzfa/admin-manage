package com.duobei.core.operation.startupPage.service;

import com.duobei.core.operation.startupPage.domain.StartupPage;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/2
 */
public interface StartUpPageService {
    /**
     * 根据appId获取
     * @param appId
     * @return
     */
    StartupPage getByAppId(Integer appId);
}
