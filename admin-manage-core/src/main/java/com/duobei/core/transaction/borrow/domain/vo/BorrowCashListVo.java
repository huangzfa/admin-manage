package com.duobei.core.transaction.borrow.domain.vo;

import com.duobei.core.transaction.borrow.domain.BorrowCash;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public class BorrowCashListVo extends BorrowCash {
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 用户手机号
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String realName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
