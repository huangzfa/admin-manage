package com.duobei.core.operation.functionSwitch.domain.vo;

import com.duobei.core.operation.functionSwitch.domain.FunctionSwitch;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/3
 */
public class FunctionSwitchAppVo extends FunctionSwitch {

    //绑定id
    private Integer switchAppId;

    //0禁用 1启用
    private Integer isEnable;

    public Integer getSwitchAppId() {
        return switchAppId;
    }

    public void setSwitchAppId(Integer switchAppId) {
        this.switchAppId = switchAppId;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}
