package com.duobei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/2
 */
@AllArgsConstructor
public enum BusinessEnum {

    XFD("xfd", "消费贷"),
    XFFQ("xffq", "消费分期"),
    XJD("xjd", "现金贷");

    @Getter
    private String code;
    @Getter
    private String name;

    public static BusinessEnum findByCode(String code) {
        for (BusinessEnum reviewState : BusinessEnum.values()) {
            if (reviewState.getCode().equals(code)) {
                return reviewState;
            }
        }
        return null;
    }

}
