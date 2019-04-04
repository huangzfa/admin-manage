package com.duobei.core.transaction.renewal.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
public class BorrowCashRenewalCriteria extends Pagination {
    /**
     * 用户手机号
     */
    private String userName;

    /**
     * 借款流水号
     */
    private String borrowNo;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 借款Ids
     */
    private List<Long> borrowIds;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getBorrowIds() {
        return borrowIds;
    }

    public void setBorrowIds(List<Long> borrowIds) {
        this.borrowIds = borrowIds;
    }
}
