package com.duobei.core.operation.app.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;

public interface AppPageConfigService {


    /**
     * 列表查询
     * @param appPageConfigCriteria
     * @return
     */
    ListVo<AppPageConfig> queryAppPageConfigList(AppPageConfigCriteria appPageConfigCriteria);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AppPageConfig queryAppPageConfigById(Integer id);

    /**
     * 删除应用标签配置
     * @param id
     */
    void deleteAppPageConfig(AppPageConfig id) throws TqException;

    /**
     * 新增应用标签配置
     * @param appPageConfig
     */
    void addAppPageConfig(AppPageConfig appPageConfig) throws TqException;

    /**
     * 修改应用标签配置
     * @param appPageConfig
     */
    void updateAppPageConfig(AppPageConfig appPageConfig) throws TqException;

    /**
     * 禁用/启用
     * @param appPageConfig
     */
    void updateIsEnable(AppPageConfig appPageConfig) throws TqException;
}
