package com.duobei.core.user.user.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/19
 */
public class UserBorrowListVo  extends BorrowCash {
    /**
     * 借款状态（显示）
     */
    private String borrowStateName;
    /**
     * 风控状态（显示）
     */
    private String riskStateName;
    /**
     * 待还金额
     */
    private BigDecimal waitAmount;
    /**
     * 待还逾期费
     */
    private BigDecimal waitOverdueAmount;

    public String getBorrowStateName() {
        return borrowStateName;
    }

    public void setBorrowStateName(String borrowStateName) {
        this.borrowStateName = borrowStateName;
    }

    public String getRiskStateName() {
        return riskStateName;
    }

    public void setRiskStateName(String riskStateName) {
        this.riskStateName = riskStateName;
    }

    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }

    public BigDecimal getWaitOverdueAmount() {
        return waitOverdueAmount;
    }

    public void setWaitOverdueAmount(BigDecimal waitOverdueAmount) {
        this.waitOverdueAmount = waitOverdueAmount;
    }
}
