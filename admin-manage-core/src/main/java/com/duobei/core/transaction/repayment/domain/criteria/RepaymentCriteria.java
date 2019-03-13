package com.duobei.core.transaction.repayment.domain.criteria;

import com.duobei.common.util.Pagination;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public class RepaymentCriteria extends Pagination {
    private String borroNo;

    private String repayNo;

    private String userName;

    private Long userId;

    private Integer productId;

    public String getBorroNo() {
        return borroNo;
    }

    public void setBorroNo(String borroNo) {
        this.borroNo = borroNo;
    }

    public String getRepayNo() {
        return repayNo;
    }

    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
