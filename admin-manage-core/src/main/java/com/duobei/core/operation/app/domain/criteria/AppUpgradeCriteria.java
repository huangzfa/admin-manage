package com.duobei.core.operation.app.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public class AppUpgradeCriteria extends Pagination {
    private Integer productId;

    private Integer appId;

    private List<Integer> appIds;

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

}
