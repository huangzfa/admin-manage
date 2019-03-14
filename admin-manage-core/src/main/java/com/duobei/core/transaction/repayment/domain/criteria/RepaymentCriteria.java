package com.duobei.core.transaction.repayment.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public class RepaymentCriteria extends Pagination {
    private String borrowNo;

    private String repayNo;

    private String userName;

    private Long userId;

    private List<Long> borrowIds;

    private Integer productId;

    public List<Long> getBorrowIds() {
        return borrowIds;
    }

    public void setBorrowIds(List<Long> borrowIds) {
        this.borrowIds = borrowIds;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
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
