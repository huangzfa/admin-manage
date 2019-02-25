package com.duobei.common.enums;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/1/16
 */
public enum BorrowStateEnum {
    /**
     * 【0:申请/未审核，1:已结清，2:打款中，3:打款失败，4:关闭，5:已经打款/待还款】
     */
    APPLY(0, "APPLY", "账单待审核"),//申请、未审核
    FINISH(1, "FINISH", "账单已结清"),//已结清
    IN_REMITTANCE(2, "IN_REMITTANCE", "账单打款中"),//打款中
    REMITTANCE_FAIL(3, "REMITTANCE_FAIL", "账单打款失败"),//打款失败
    CLOSED(4, "CLOSED", "账单已关闭"),//关闭
    PENDING_REPAYMENT(5, "PENDING_REPAYMENT", "账单待还款");//已经打款/待还款

    private Integer code;

    private String name;

    private String desc;

    BorrowStateEnum(Integer code,String name,String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public static BorrowStateEnum findByCode(Integer code) {
        for (BorrowStateEnum borrowState : BorrowStateEnum.values()) {
            if (borrowState.getCode().equals(code)) {
                return borrowState;
            }
        }
        return null;
    }

    public static String findDescByCode(Integer code) {
        for (BorrowStateEnum borrowState : BorrowStateEnum.values()) {
            if (borrowState.getCode().equals(code)) {
                return borrowState.getDesc();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
