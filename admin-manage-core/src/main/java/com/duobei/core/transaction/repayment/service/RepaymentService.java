package com.duobei.core.transaction.repayment.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface RepaymentService {
    ListVo<BorrowCashRepayment> getListByQuery(RepaymentCriteria repaymentCriteria);

    BorrowCashRepayment getById(Long id);
}
