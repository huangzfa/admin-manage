package com.duobei.core.transaction.borrow.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public interface BorrowCashService {
    ListVo<BorrowCash> getListByQuery(BorrowCashCriteria borrowCashCriteria);
}
