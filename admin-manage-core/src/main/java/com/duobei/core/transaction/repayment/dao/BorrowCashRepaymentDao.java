package com.duobei.core.transaction.repayment.dao;

import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface BorrowCashRepaymentDao {
    List<BorrowCashRepaymentListVo> getListVoByQuery(RepaymentCriteria repaymentCriteria);

    /**
     * 根据Id查询还款信息
     * @param id
     * @return
     */
    BorrowCashRepayment getById(Long id);

    List<BorrowCashRepaymentReportVo> getReportList(FinanceReportCriteria criteria);

    /**
     * 根据优惠券id查询借款订单
     * @param couponIds
     * @return
     */
    List<BorrowCashRepayment> getByCouponIds(@Param("couponIds") List<HashMap<String,Object>> couponIds);

    BorrowCashRepayment getByBorrowNoMap(HashMap<String,Object> map);
}
