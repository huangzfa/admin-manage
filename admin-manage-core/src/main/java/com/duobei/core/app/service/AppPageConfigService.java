package com.duobei.core.app.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.app.domain.AppPageConfig;
import com.duobei.core.app.domain.criteria.AppPageConfigCriteria;
import com.duobei.core.auth.domain.credential.OperatorCredential;

public interface AppPageConfigService {
    ListVo<AppPageConfig> queryAppPageList(OperatorCredential credential, AppPageConfigCriteria appPageConfigCriteria);

    ListVo<AppPageConfig> queryAppPageList(AppPageConfigCriteria appPageConfigCriteria);
}
