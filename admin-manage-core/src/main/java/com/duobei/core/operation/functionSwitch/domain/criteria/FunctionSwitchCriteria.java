package com.duobei.core.operation.functionSwitch.domain.criteria;

import com.duobei.common.util.Pagination;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/02
 */
public class FunctionSwitchCriteria extends Pagination {
    private Integer appId;
    private String functionCode;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
