package com.duobei.core.transaction.borrow.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public class BorrowCashCriteria extends Pagination {

    /**
     * 服务化订单号
     */
    private String borrowNo;

    /**
     * 注册手机号
     */
    private String userName;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 用户id
     */
    private Long userId;

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
}
