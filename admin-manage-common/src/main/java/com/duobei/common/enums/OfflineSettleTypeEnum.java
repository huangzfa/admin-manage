package com.duobei.common.enums;

/**
 * 线下还款 提交人类型
 */
public enum OfflineSettleTypeEnum {

    OFFLINE_SETTLE_BATCH_FLAT(1,"批量平账"),
    OFFLINE_SETTLE_HUMAN(2,"人工平账")
    ;
    OfflineSettleTypeEnum(int type,String desc) {
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
