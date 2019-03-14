package com.duobei.core.transaction.repayment.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentVo;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface RepaymentService {
    ListVo<BorrowCashRepaymentListVo> getListByQuery(RepaymentCriteria repaymentCriteria);

    BorrowCashRepaymentVo getById(Long id) ;
}
