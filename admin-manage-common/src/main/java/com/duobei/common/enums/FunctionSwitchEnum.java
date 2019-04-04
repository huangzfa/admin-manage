package com.duobei.common.enums;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/2
 */
public enum FunctionSwitchEnum {

    NO_SHTUDENT_AUTH_POPUP( "NO_SHTUDENT_AUTH_POPUP", "非学生认证"),
    FORCE_LOGIN( "FORCE_LOGIN", "强制登录"),
    EMERGENCY_CONTACT( "EMERGENCY_CONTACT", "紧急联系人");

    private String code;

    private String name;

    FunctionSwitchEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public static FunctionSwitchEnum findByCode(String code) {
        for (FunctionSwitchEnum borrowState : FunctionSwitchEnum.values()) {
            if (borrowState.getCode().equals(code)) {
                return borrowState;
            }
        }
        return null;
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
