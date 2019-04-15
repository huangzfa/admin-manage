package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动类型枚举
 *
 * @author ginko
 */
@AllArgsConstructor
public enum ActivityTypeEnum {
    HONGBAO(1,"hongbao","红包活动"),
    ZHUANPAN(2,"zhuanpan","转盘活动"),
    STATIC(3,"static","静态模板"),
    EXCHANGE(4,"exchange","兑换模板");
    @Getter
    private Integer code;
    @Getter
    private String env;
    @Getter
    private String desc;


    public static String findEnvByCode(Integer code) {
        for (ActivityTypeEnum environmentType : ActivityTypeEnum.values()) {
            if (environmentType.getCode().intValue() == code.intValue()) {
                return environmentType.getEnv();
            }
        }
        return null;
    }


    public static Integer findCodeByEnv(String env) {
        for (ActivityTypeEnum environmentType : ActivityTypeEnum.values()) {
            if (environmentType.getEnv().equals(env)) {
                return environmentType.getCode();
            }
        }
        return null;
    }

}
