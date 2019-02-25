package com.duobei;

public enum  ResponseCode {
    SUCCESS(200,"success"),

    IS_SUCCESS(200,"案件已经减免成功"),
    PARARMENT_ERROR(400,"参数异常"),
    SIGN_ERROR(403,"验签失败"),
    SYSYTEM_ERROR(500,"系统异常"),

    //逾期减免申请异常
    BORROW_REDUCE_AMOUNT_UPDATE(1000,"账单待还逾期费已改变"),
    OVERDUE_APPLY_FAIL(1002,"系统同步数据失败"),
    OVERDUE_NOTFOUND(1003,"没有查询到该案件符合的借款单据"),
    OVERDUE_END(1004,"借款单已完结"),
    OVERDUE_REDUCE_AOMUNT_ERROR(1005,"逾期减免金额异常"),
    OVERDUE_REDUCE_STAGE_AOMUNT_ERROR(1006,"减免金额不能大于当前逾期费"),
    OVERDU_REDUCE_FAIL(1007,"减免失败");
    private Integer code;

    private String msg;

    ResponseCode() {
    }

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
