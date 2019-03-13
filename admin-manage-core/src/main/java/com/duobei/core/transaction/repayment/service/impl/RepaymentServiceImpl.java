package com.duobei.core.transaction.repayment.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.repayment.dao.BorrowCashRepaymentDao;
import com.duobei.core.transaction.repayment.dao.mapper.BorrowCashRepaymentMapper;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.service.RepaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("repaymentService")
public class RepaymentServiceImpl implements RepaymentService {
    @Resource
    BorrowCashRepaymentDao repaymentDao;

    @Resource
    BorrowCashRepaymentMapper repaymentMapper;

    @Override
    public ListVo<BorrowCashRepayment> getListByQuery(RepaymentCriteria repaymentCriteria) {
        return null;
    }

    @Override
    public BorrowCashRepayment getById(Long id) {
        return null;
    }
}
