package com.duobei.core.transaction.repayment.domain.vo;

import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/12
 */
public class BorrowCashRepaymentReportVo extends BorrowCashRepayment {
    private String productName;

    private String borrowNo;

    private Date gmtPlanRepayment;

    private String repayStateName;

    private String repayTypeName;

    private BigDecimal repayAmountDecimal;

    private BigDecimal repayActualAmountDecimal;

    private BigDecimal unpaidAmountDecimal;

    private BigDecimal rebateAmountDecimal;

    private BigDecimal couponAmountDecimal;

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

    public Date getGmtPlanRepayment() {
        return gmtPlanRepayment;
    }

    public void setGmtPlanRepayment(Date gmtPlanRepayment) {
        this.gmtPlanRepayment = gmtPlanRepayment;
    }

    public String getRepayStateName() {
        return repayStateName;
    }

    public void setRepayStateName(String repayStateName) {
        this.repayStateName = repayStateName;
    }

    public String getRepayTypeName() {
        return repayTypeName;
    }

    public void setRepayTypeName(String repayTypeName) {
        this.repayTypeName = repayTypeName;
    }

    public BigDecimal getRepayAmountDecimal() {
        return repayAmountDecimal;
    }

    public void setRepayAmountDecimal(BigDecimal repayAmountDecimal) {
        this.repayAmountDecimal = repayAmountDecimal;
    }

    public BigDecimal getRepayActualAmountDecimal() {
        return repayActualAmountDecimal;
    }

    public void setRepayActualAmountDecimal(BigDecimal repayActualAmountDecimal) {
        this.repayActualAmountDecimal = repayActualAmountDecimal;
    }

    public BigDecimal getUnpaidAmountDecimal() {
        return unpaidAmountDecimal;
    }

    public void setUnpaidAmountDecimal(BigDecimal unpaidAmountDecimal) {
        this.unpaidAmountDecimal = unpaidAmountDecimal;
    }

    public BigDecimal getRebateAmountDecimal() {
        return rebateAmountDecimal;
    }

    public void setRebateAmountDecimal(BigDecimal rebateAmountDecimal) {
        this.rebateAmountDecimal = rebateAmountDecimal;
    }

    public BigDecimal getCouponAmountDecimal() {
        return couponAmountDecimal;
    }

    public void setCouponAmountDecimal(BigDecimal couponAmountDecimal) {
        this.couponAmountDecimal = couponAmountDecimal;
    }
}
