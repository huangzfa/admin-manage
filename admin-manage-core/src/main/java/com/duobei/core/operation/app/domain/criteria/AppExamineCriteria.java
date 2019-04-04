package com.duobei.core.operation.app.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public class AppExamineCriteria extends Pagination {
    private Integer productId;

    private Integer appId;

    private List<Integer> appIds;

    private String channelName;

    private List<Integer> channelIds;

    private Integer versionNumber;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public List<Integer> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<Integer> appIds) {
        this.appIds = appIds;
    }

    public List<Integer> getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(List<Integer> channelIds) {
        this.channelIds = channelIds;
    }
}
