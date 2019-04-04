package com.duobei.core.transaction.repayment.dao;

import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import org.apache.ibatis.annotations.Param;

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
}
