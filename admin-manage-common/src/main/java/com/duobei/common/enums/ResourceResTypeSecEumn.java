package com.duobei.common.enums;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public enum ResourceResTypeSecEumn {
    COMSUMDEBTORDER_REPORT_MAX_NUM("COMSUMDEBTORDER_REPORT_MAX_NUM","借贷商品订单最大导出数量")
    ;


    private String code;
    private String name;

    ResourceResTypeSecEumn(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
