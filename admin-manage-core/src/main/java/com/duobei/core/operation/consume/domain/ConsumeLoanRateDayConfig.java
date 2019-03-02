package com.duobei.core.operation.consume.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConsumeLoanRateDayConfig implements Serializable {
    /**
     * 主键，自增id
     */
    private Integer id;

    /**
     * 消费贷配置ID
     */
    private Integer consumeLoanConfigId;

    /**
     * 日利率
     */
    private BigDecimal dayRate;

    /**
     * 借款期限天数,JSON数组格式
     */
    private String borrowDays;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consume_loan_rate_day_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    private static final long serialVersionUID = 3223801780927162371L;

}