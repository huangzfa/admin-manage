package com.duobei.core.transaction.repayment.domain.vo;

import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import lombok.Data;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */
@Data
public class RepaymentOfflineVo extends RepaymentOffline {


    /**
     * 用户手机号
     */
    private String phone;


    /**
     * 借款金额
     */
    private Long amount;

    private Date borrowTime;

    /**
     * 累计已还逾期费
     */
    private Long sumOverdueAmount;

    /**
     * 续借次数
     */
    private Integer renewalNum;

    /**
     * 逾期费
     */
    private Long realOverdueAmount;

    /**
     * 累计已还金额
     */
    private Long repayAmount;

    /**
     * 待还金额
     */
    private Long repaymentAmount;

    /**
     * 操作人员
     */
    private String modifyOperatorName;
}
