package com.duobei.core.user.user.domain.criteria;

import com.duobei.common.util.Pagination;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/19
 */
public class UserBorrowCriteria extends Pagination {
    private Long userId;

    private List<Integer> borrowState;

    private Integer stateQueryType;

    public Integer getStateQueryType() {
        return stateQueryType;
    }

    public void setStateQueryType(Integer stateQueryType) {
        this.stateQueryType = stateQueryType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(List<Integer> borrowState) {
        this.borrowState = borrowState;
    }
}
