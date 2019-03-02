package com.duobei.core.operation.consume.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConsumeLoanRenewalConfig implements Serializable {
    /**
     * 主键，自增id
     */
    private Integer id;

    /**
     * 消费贷配置ID
     */
    private Integer consumeLoanConfigId;

    /**
     * 逾期起始天数，包含，为0表示不逾期
     */
    private Integer startDay;

    /**
     * 逾期结束天数，包含，为0表示不逾期
     */
    private Integer endDay;

    /**
     * 续借需还本金比例，
     */
    private BigDecimal renewalCapitalRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consume_loan_renewal_config
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    private static final long serialVersionUID = 645009468901497309L;


}