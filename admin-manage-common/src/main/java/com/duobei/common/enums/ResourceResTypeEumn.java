package com.duobei.common.enums;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public enum  ResourceResTypeEumn {
    BIZ_CONFIG("BIZ_CONFIG","业务资源配置")
    ;


    private String code;
    private String name;

    ResourceResTypeEumn(String code, String name) {
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
