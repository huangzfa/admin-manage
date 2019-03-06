package com.duobei.core.operation.app.dao;

import com.duobei.core.operation.app.domain.AppPageConfig;
import com.duobei.core.operation.app.domain.criteria.AppPageConfigCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public interface AppPageConfigDao {

    int updateAppPageConfig(AppPageConfig appPageConfig);

    int deleteAppPageConfig(AppPageConfig appPageConfig);

    int addAppPageConfig(AppPageConfig appPageConfig);


    List<AppPageConfig> getListByQuery(AppPageConfigCriteria appPageConfigCriteria);

    AppPageConfig getAppPageConfigById(Integer id);

    int updateIsEnable(AppPageConfig appPageConfig);
}