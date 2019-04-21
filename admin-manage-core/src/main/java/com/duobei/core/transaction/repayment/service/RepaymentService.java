package com.duobei.core.transaction.repayment.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentReportVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface RepaymentService {
    ListVo<BorrowCashRepaymentListVo> getListByQuery(RepaymentCriteria repaymentCriteria);

    BorrowCashRepaymentVo getById(Long id) ;

    List<BorrowCashRepaymentReportVo> getReportList(FinanceReportCriteria criteria);
}
