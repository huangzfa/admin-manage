package com.duobei.core.transaction.borrow.dao;

import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;


import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public interface BorrowCashDao {
    List<BorrowCashListVo> getListVoByQuery(BorrowCashCriteria borrowCashCriteria);

    BorrowCash getById(Long borrowId);

}
