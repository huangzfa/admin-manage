package com.duobei.common.enums;

public enum MsgPushType {

    OFFLINE_REPAYMENT_PUSH_RONG360(1,"线下还款推送融360"),
    REPAYMENT_INFO_PUSH_COLLECTION(2,"还款信息推送催收系统"),
    REPAYMENT_REDUCE_PUSH_RONG360(3,"还款减免推送还款计划到融360"),
    CALC_OVERDUE_PUSH_RONG360(4,"逾期计算推送还款计划到融360")
    ;
    MsgPushType(int type,String desc) {
        this.type = type;
        this.desc = desc;
    }

    private int type;

    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }
}
