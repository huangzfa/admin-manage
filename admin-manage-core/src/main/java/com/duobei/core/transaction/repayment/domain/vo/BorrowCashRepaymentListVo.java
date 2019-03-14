package com.duobei.core.transaction.repayment.domain.vo;

import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public class BorrowCashRepaymentListVo extends BorrowCashRepayment {

    /**
     * 借款编号(服务订单号)
     */
    private String borrowNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 用户手机号
     */
    private String userName;

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
