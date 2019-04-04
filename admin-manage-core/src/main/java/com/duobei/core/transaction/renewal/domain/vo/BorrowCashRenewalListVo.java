package com.duobei.core.transaction.renewal.domain.vo;

import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public class BorrowCashRenewalListVo extends BorrowCashRenewal {
    /**
     * 借款编号
     */
    private String borrowNo;

    /**
     * 用户手机号
     */
    private String userName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 用户姓名
     */
    private String realName;

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
