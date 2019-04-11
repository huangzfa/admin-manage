package com.duobei.common.enums;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public enum ConsumdebtOrderStateEumn {
    NEW(0,"新建"),
    RECEIVED(1,"已收货"),
    WAIT_DELIVERY(2,"待发货"),
    SHIPPED(3,"已发货"),
    CLOSE(4,"关闭");

    private Integer code;
    private String name;
    public static String findDescByCode(Integer code) {
        for (ConsumdebtOrderStateEumn consumdebtOrderStateEumn : ConsumdebtOrderStateEumn.values()) {
            if (consumdebtOrderStateEumn.getCode().equals(code)) {
                return consumdebtOrderStateEumn.getName();
            }
        }
        return null;
    }

    ConsumdebtOrderStateEumn(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
