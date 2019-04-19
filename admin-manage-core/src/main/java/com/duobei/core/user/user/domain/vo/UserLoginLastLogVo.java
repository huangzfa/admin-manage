package com.duobei.core.user.user.domain.vo;

import com.duobei.core.operation.app.domain.App;
import com.duobei.core.user.user.domain.UserLoginLog;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/17
 */
public class UserLoginLastLogVo extends UserLoginLog {
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
