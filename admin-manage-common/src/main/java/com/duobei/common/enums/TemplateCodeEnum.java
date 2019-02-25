package com.duobei.common.enums;

/**短信模板枚举
 * @author huangzhongfa
 * @description
 * @date 2019/1/17
 */
public enum TemplateCodeEnum {
    ADMIN_LOGIN_VERIFY("ADMIN_LOGIN_VERIFY",3, "多贝后台验证码登录"),
    ADMIN_MODIFY_PASSWORD("ADMIN_MODIFY_PASSWORD",4, "修改密码");

    TemplateCodeEnum(String code,int type, String msg) {
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    private String code;
    private int type;
    private String msg;

    public static String findByType(int type) {
        for (TemplateCodeEnum code : TemplateCodeEnum.values()) {
            if (code.getType() == type) {
                return code.getCode();
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
