package com.duobei.common.enums;

/**
 * 文案类型枚举
 *
 * @author 黄中发
 */
public enum CopywritingType {



    /**
     * 打款中 提示信息资源
     **/
    IN_REMITTANCE_RES("IN_REMITTANCE",  "打款中 提示信息资源"),

 

    /**
     * 人审中 提示信息资源
     **/
    MAN_AUDITING_RES("MAN_AUDITING", "人审中 提示信息资源"),

   
    /**
     * 额度获取中 提示信息资源
     **/
    QUOTA_ACQUIRING_RES("QUOTA_ACQUIRING", "额度获取中 提示信息资源"),

    /**
     * 机审中 提示信息资源
     **/
    RISK_AUDITING_RES("RISK_AUDITING", "机审中 提示信息资源"),

    /**
     * 风控审核等待
     **/
    SUBMIT_STAGE_BORROW_RISK_WAIT_RES("SUBMIT_STAGE_BORROW_RISK_WAIT", "风控审核等待"),

    /**
     * 风控准入通过
     **/
    SUBMIT_STAGE_BORROW_RISK_PASS_RES("SUBMIT_STAGE_BORROW_RISK_PASS", "风控准入通过"),

    /**
     * 风控准入拒绝
     **/
    SUBMIT_STAGE_BORROW_RISK_REFUSE_RES("SUBMIT_STAGE_BORROW_RISK_REFUSE", "风控准入拒绝"),

    /**
     * 风控人审通过
     **/
    SUBMIT_STAGE_BORROW_RISK_MANUAL_AUDIT_RES("SUBMIT_STAGE_BORROW_RISK_MANUAL_AUDIT", "风控人审通过"),
	
    /**
     * 支付宝还款提交成功页文案配置
     */
    OFFLINE_REPAY_SUBMIT_SUCCESS_RES("OFFLINE_REPAY_SUBMIT_SUCCESS",  "支付宝还款提交成功页文案配置"),

    /**
     * 支付宝还款提交失败页文案配置
     */
    OFFLINE_REPAY_SUBMIT_FAIL_RES("OFFLINE_REPAY_SUBMIT_FAIL",  "支付宝还款提交失败页文案配置"),

    /**
     * 逾期1-3天
     */
    OVERDUE_THREEDAY_FLOLLW_RES("OVERDUE_3DAYS_BELLOW", "逾期1-3天文案"),

    /**
     * 逾期4天以上
     */
    OVERDUE_FORUDAY_MORE_RES("OVERDUE_4DAYS_MORE","逾期4天以上文案"),

    /**
     * 还款处理中文案
     */
    IN_REPAYMENT("IN_REPAYMENT",  "还款处理中"),


    /**
     * 银行卡还款反馈页文案
     */
    BANK_REPAY_FEEDBACK_RES("BANK_REPAY_FEEDBACK", "银行卡还款反馈页"),

    ;




    CopywritingType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type;

    

    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

 
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
