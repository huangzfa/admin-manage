package com.duobei.core.operation.app.domain.vo;

import com.duobei.core.operation.app.domain.AppExamine;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public class AppExamineVo extends AppExamine {
    private String channelName;
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
