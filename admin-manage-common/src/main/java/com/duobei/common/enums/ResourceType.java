package com.duobei.common.enums;

/**
 * 资源类型枚举
 *
 * @author ginko
 */
public enum ResourceType {

    /**
     * 启动页资源
     **/
    APP_LAUNCH_IMAGE_RES("APP_LAUNCH_IMAGE", "", "启动页资源"),

    /**
     * 风控认证重置时间间隔资源
     **/
    RISK_RESET_TIME("RISK_RESET_TIME", "", "风控认证重置时间间隔资源"),

    /**
     * 借钱首页用户审核被拒后在冷却期内借钱主页显示贷超资源
     **/
    APP_HOME_LOANSHOP_RES("APP_HOME_LOANSHOP", "", "借钱首页用户审核被拒后在冷却期内借钱主页显示贷超资源"),

    /**
     * 贷超 提示信息资源
     **/
    CAN_NOT_BORROW_RES("CAN_NOT_BORROW", "", "贷超 提示信息资源"),

    /**
     * 还款中 提示信息资源
     **/
    IN_REPAYMENT_RES("IN_REPAYMENT", "", "还款中 提示信息资源"),


    /**
     * 逾期中 提示信息资源
     **/
    OVERDUE_RES("OVERDUE", "", "逾期中 提示信息资源"),

    /**
     * 待还款 提示信息资源
     **/
    PENDING_REPAYMENT_RES("PENDING_REPAYMENT", "", "待还款 提示信息资源"),

    /**
     * 额度已获取 提示信息资源
     **/
    QUOTA_ACQUIRED_RES("QUOTA_ACQUIRED", "", "额度已获取 提示信息资源"),


    RISK_SCENECODE("RISK_SCENECODE", "", "风控场景编码"),

    /**
     * 线下还款H5页面配置资源
     **/
    OFFLINE_REPAY_H5_RES("OFFLINE_REPAY_H5", "", "线下还款H5页面配置资源"),
    /**
     * 非学生身份确认配置资源
     */
    CHECK_STUDENT_RES("CHECK_STUDENT_RES", "", "非学生身份确认配置资源"),



    /**
     * 借钱提交-文案共同配置
     */
    SUBMIT_STAGE_BORROW_RISK_COMMON_RES("SUBMIT_STAGE_BORROW_RISK_COMMON_RES", "", "借钱提交-文案共同配置"),

    /**
     * 借钱提交-风控准入通过处理文案配置
     */
    SUBMIT_STAGE_BORROW_RISK_PASS_RES("SUBMIT_STAGE_BORROW", "RISK_PASS", "借钱提交-风控准入通过处理文案配置"),
    /**
     * 借钱提交-风控准入拒绝处理文案配置
     */
    SUBMIT_STAGE_BORROW_RISK_REFUSE_RES("SUBMIT_STAGE_BORROW", "RISK_REFUSE", "借钱提交-风控准入拒绝处理文案配置"),
    /**
     * 借钱提交-风控人审处理文案配置
     */
    SUBMIT_STAGE_BORROW_RISK_MANUAL_AUDIT_RES("SUBMIT_STAGE_BORROW", "RISK_MANUAL_AUDIT", "借钱提交-风控人审处理文案配置"),

    /**
     * UPS代付提交成功-文案配置
     */
    UPS_SUBMIT_SUCCESS_RES("UPS_SUBMIT_SUCCESS", "", "UPS代付提交成功文案配置"),
    /**
     * UPS代付提交失败-文案配置
     */
    UPS_SUBMIT_FAIL_RES("UPS_SUBMIT_FAIL", "", "UPS代付提交失败文案配置"),

    /**
     * 还款最低限额
     */
    REPAY_MIN_AMOUNT_RES("REPAY_MIN_AMOUNT", "", "最低还款限额配置"),

    /**
     * 线下还款处理中
     */
    OFFLINE_REPAY_SUBMIT_PROGRESS_RES("OFFLINE_REPAY_SUBMIT_PROGRESS", "", "线下还款处理中"),


    MODIFY_BORROW_RELATED_AMOUNT("MODIFY_BORROW_RELATED_AMOUNT","","Redis分布式锁key"),

    OVERDUE_LIMIT_RATE("OVERDUE_LIMIT_RATE","","逾期费上限比例配置"),

    RONG360_PRODUCT_ID_LIST("RONG360_PRODUCT_ID_LIST","","融360相关产品id集合")
    ;




    ResourceType(String type, String secType,String desc) {
        this.type = type;
        this.secType = secType;
        this.desc = desc;
    }

    private String type;

    private String secType;

    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
