package com.duobei.core.transaction.repayment.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RepaymentOffline implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 借钱id
     */
    private Long borrowCashId;

    /**
     * 是否结清【0：初始，1：结清，-1：未结清】
     */
    private Integer isFinish;

    /**
     * 还款还款【0：初始，1：还款成功，-1：还款失败】
     */
    private Integer state;

    /**
     * 还款金额
     */
    private Long amount;

    /**
     * 实还金额
     */
    private Long repayAmount;

    /**
     * 减免金额
     */
    private Long derateAmount;

    /**
     * 实际还款时间
     */
    private Date gmtRealRepay;

    /**
     * 待还金额
     */
    private Long arrearsAmount;

    /**
     * 还款凭证图片url
     */
    private String imgUrl;

    /**
     * 提交转账订单号
     */
    private String tradeNo;

    /**
     * 提交人类型：0：用户，1：催收系统，2：公司业务人员
     */
    private Integer submitterType;

    /**
     * 提交人id
     */
    private Long submitterId;

    /**
     * 提交人姓名，提交人类型为0时脱敏，1时不脱敏
     */
    private String submitterName;

    /**
     * 提交时间
     */
    private Date submitterTime;

    /**
     * 平账人id
     */
    private Integer settleOperatorId;

    /**
     * 平账人姓名
     */
    private String settleOperatorName;

    /**
     * 平账类型【1：批量平账，2：人工平帐】
     */
    private Integer settleType;

    /**
     * 平账时间
     */
    private Date settleTime;

    /**
     * 还款账号类型：bank银行卡、zfb支付宝
     */
    private String accountType;

    /**
     * 还款账号（银行卡号、支付宝账号）脱敏
     */
    private String accountNo;

    /**
     * 还款账号密文无解，用于匹配
     */
    private String accountNoMd5;

    /**
     * 还款账号密文可解，如果是银行卡并且不是用户已绑定的卡则需要处理
     */
    private String accountNoEncrypt;

    /**
     * 还款账号名（银行卡名称、支付宝名称）
     */
    private String accountName;

    /**
     * 到账支付宝账号配置id
     */
    private Integer zfbAccountId;

    /**
     * 到账支付宝账号
     */
    private String zfbAccount;

    /**
     * 是否作废：默认0；1作废
     */
    private Byte isInvalid;

    /**
     * 是否作废操作人id
     */
    private Long invalidOperatorId;

    /**
     * 是否作废的操作时间
     */
    private Date invalidModify;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private Integer addOperatorId;

    /**
     * 
     */
    private Integer modifyOperatorId;

    /**
     * 0.未删除, 1.删除
     */
    private Long isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tt_borrow_cash_repayment_offline
     *
     * @mbg.generated Mon May 20 15:20:46 CST 2019
     */
    private static final long serialVersionUID = 5331658308956991643L;


}