package com.duobei.core.transaction.repayment.domain.bo;

import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/21
 */
@Data
public class RepaymentOfflineBo{
    private String balanceType;

    /**
     * 流水号
     */
    private String repayNo;

    /**
     * 还款方式
     */
    private String repayType;

    /**
     * 实还金额
     */
    private String actualAmount;


    /**
     * 减免金额
     */
    private String deductAmount;

    private String zfbAccountId;

    private String zfbAccount;

    private String repayImgUrl;

    private String operatorId;

    private String operatorName;

    private String repayCardNo;

    private String remark;
}
