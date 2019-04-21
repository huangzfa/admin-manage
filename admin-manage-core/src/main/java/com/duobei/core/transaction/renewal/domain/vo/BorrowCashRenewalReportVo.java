package com.duobei.core.transaction.renewal.domain.vo;

import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
public class BorrowCashRenewalReportVo extends BorrowCashRenewal {
    private String productName;

    private String borrowNo;

    private String renewalStateName;

    private BigDecimal capitalAmountDecimal;

    private BigDecimal rebateAmountDecimal;

    private BigDecimal actualAmountDecimal;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }

    public String getRenewalStateName() {
        return renewalStateName;
    }

    public void setRenwalStateName(String renewalStateName) {
        this.renewalStateName = renewalStateName;
    }

    public BigDecimal getCapitalAmountDecimal() {
        return capitalAmountDecimal;
    }

    public void setCapitalAmountDecimal(BigDecimal capitalAmountDecimal) {
        this.capitalAmountDecimal = capitalAmountDecimal;
    }

    public BigDecimal getRebateAmountDecimal() {
        return rebateAmountDecimal;
    }

    public void setRebateAmountDecimal(BigDecimal rebateAmountDecimal) {
        this.rebateAmountDecimal = rebateAmountDecimal;
    }

    public BigDecimal getActualAmountDecimal() {
        return actualAmountDecimal;
    }

    public void setActualAmountDecimal(BigDecimal actualAmountDecimal) {
        this.actualAmountDecimal = actualAmountDecimal;
    }
}
