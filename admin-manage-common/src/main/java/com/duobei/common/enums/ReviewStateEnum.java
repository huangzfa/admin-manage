package com.duobei.common.enums;

/**
 * @author huanghongfa
 * @description 审核状态
 * @date 2018/11/30 13:13
 */
public enum ReviewStateEnum {
    /**
     * 【0:申请/未审核，1:风控同意，2:机审中，3:人审中，4:风控拒绝】
     */
    DEFAULT(0, "DEFAULT"),
    RISK_AGREE(1, "RISK_AGREE"),
    MACHINE_AUDIT(2, "MACHINE_AUDIT"),
    MANUAL_AUDIT(3, "MANUAL_AUDIT"),
    RISK_REFUSE(4, "RISK_REFUSE");

    private Integer code;

    private String name;
    ReviewStateEnum(Integer code,String name) {
        this.name = name;
        this.code = code;
    }
    public static ReviewStateEnum findByCode(Integer code) {
        for (ReviewStateEnum reviewState : ReviewStateEnum.values()) {
            if (reviewState.getCode().equals(code)) {
                return reviewState;
            }
        }
        return null;
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
