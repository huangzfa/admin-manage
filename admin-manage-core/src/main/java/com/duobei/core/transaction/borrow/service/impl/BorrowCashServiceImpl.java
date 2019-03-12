package com.duobei.core.transaction.borrow.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import org.springframework.stereotype.Service;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Service("borrowCashService")
public class BorrowCashServiceImpl implements BorrowCashService {
    @Override
    public ListVo<BorrowCash> getListByQuery(BorrowCashCriteria borrowCashCriteria) {
        return null;
    }
}
