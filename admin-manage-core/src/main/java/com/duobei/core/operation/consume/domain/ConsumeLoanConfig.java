package com.duobei.core.operation.consume.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ConsumeLoanConfig implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 显示最低借款额度，分
     */
    private Long showMinAmount;

    /**
     * 显示最高借款额度，分
     */
    private Long showMaxAmount;

    /**
     * 显示日利率
     */
    private BigDecimal showDayRate;

    /**
     * 可借款期限天数,JSON数组格式
     */
    private String showBorrowDays;

    /**
     * 每日放款金额限制，分
     */
    private Long dayAmountLimit;

    /**
     * 真实可借款最低借款额度，分
     */
    private Long minAmount;

    /**
     * 真实可借款最高借款额度，分
     */
    private Long maxAmount;

    /**
     * 央行基准利率
     */
    private BigDecimal baseBankRate;

    /**
     * 服务费率（手续费率）
     */
    private BigDecimal poundageRate;

    /**
     * 逾期费率（日）
     */
    private BigDecimal overdueRate;

    /**
     * 最高逾期费比例
     */
    private BigDecimal maxOverdueRate;

    /**
     * 续借天数
     */
    private Integer renewalDay;

    /**
     * 续借需还本金比例，
     */
    private BigDecimal renewalCapitalRate;

    /**
     * 续借需还本金比例方式：【1：逾期天数，2：续借次数】
     */
    private Integer renewalCapitalRateType;

    /**
     * 续借金额限制，分，如本金大于100才能续借
     */
    private Long renewalAmount;

    /**
     * 可续借日期限制，比如当前日期与预计还款日期的天数差小于10
     */
    private Integer canRenewalDayLimit;

    /**
     * 是否开启几选几，1开启，0不开启
     */
    private Boolean authSeleteEnable;

    /**
     * 几选几规则：3选2为3s2
     */
    private String authSeleteRule;

    /**
     * 额度风控场景编码
     */
    private String quotaSceneCode;

    /**
     * 借款风控场景编码-首次新用户
     */
    private String borrowSceneCodeFirst;

    /**
     * 借款风控场景编码-非首次老用户
     */
    private String borrowSceneCode;

    /**
     * 数据版本号，乐观锁
     */
    private Integer dataVersion;

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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consume_loan_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    private static final long serialVersionUID = -1297522912989247915L;

}