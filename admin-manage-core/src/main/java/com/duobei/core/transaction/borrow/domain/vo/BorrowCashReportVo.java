package com.duobei.core.transaction.borrow.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
public class BorrowCashReportVo extends BorrowCash {
    private String productName;

    private String borrowStateName;

    private String riskStateName;

    /**
     * 待还逾期费金额
     */
    private BigDecimal waitOverdueAmountDeciaml;

    /**
     * 待还金额
     */
    private BigDecimal waitAmountDeciaml;

    private BigDecimal amountDeciaml;

    private BigDecimal poundageDeciaml;

    private BigDecimal activityAmountDeciaml;

    private BigDecimal arrivalAmountDeciaml;

    private BigDecimal sumOverdueAmountDeciaml;

    private BigDecimal repayAmountDeciaml;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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

    public BigDecimal getWaitOverdueAmountDeciaml() {
        return waitOverdueAmountDeciaml;
    }

    public void setWaitOverdueAmountDeciaml(BigDecimal waitOverdueAmountDeciaml) {
        this.waitOverdueAmountDeciaml = waitOverdueAmountDeciaml;
    }

    public BigDecimal getWaitAmountDeciaml() {
        return waitAmountDeciaml;
    }

    public void setWaitAmountDeciaml(BigDecimal waitAmountDeciaml) {
        this.waitAmountDeciaml = waitAmountDeciaml;
    }

    public BigDecimal getAmountDeciaml() {
        return amountDeciaml;
    }

    public void setAmountDeciaml(BigDecimal amountDeciaml) {
        this.amountDeciaml = amountDeciaml;
    }

    public BigDecimal getPoundageDeciaml() {
        return poundageDeciaml;
    }

    public void setPoundageDeciaml(BigDecimal poundageDeciaml) {
        this.poundageDeciaml = poundageDeciaml;
    }

    public BigDecimal getActivityAmountDeciaml() {
        return activityAmountDeciaml;
    }

    public void setActivityAmountDeciaml(BigDecimal activityAmountDeciaml) {
        this.activityAmountDeciaml = activityAmountDeciaml;
    }

    public BigDecimal getArrivalAmountDeciaml() {
        return arrivalAmountDeciaml;
    }

    public void setArrivalAmountDeciaml(BigDecimal arrivalAmountDeciaml) {
        this.arrivalAmountDeciaml = arrivalAmountDeciaml;
    }

    public BigDecimal getSumOverdueAmountDeciaml() {
        return sumOverdueAmountDeciaml;
    }

    public void setSumOverdueAmountDeciaml(BigDecimal sumOverdueAmountDeciaml) {
        this.sumOverdueAmountDeciaml = sumOverdueAmountDeciaml;
    }

    public BigDecimal getRepayAmountDeciaml() {
        return repayAmountDeciaml;
    }

    public void setRepayAmountDeciaml(BigDecimal repayAmountDeciaml) {
        this.repayAmountDeciaml = repayAmountDeciaml;
    }
}
