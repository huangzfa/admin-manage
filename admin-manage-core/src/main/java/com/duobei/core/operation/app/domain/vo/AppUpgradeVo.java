package com.duobei.core.operation.app.domain.vo;

import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.AppUpgrade;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public class AppUpgradeVo extends AppUpgrade {
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
