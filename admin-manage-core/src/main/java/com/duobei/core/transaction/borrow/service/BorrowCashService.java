package com.duobei.core.transaction.borrow.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashReportVo;
import com.duobei.core.user.user.domain.criteria.UserBorrowCriteria;
import com.duobei.core.user.user.domain.vo.UserBorrowListVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public interface BorrowCashService {
    /**
     * 查询借款列表
     * @param borrowCashCriteria
     * @return
     */
    ListVo<BorrowCashListVo> getListByQuery(BorrowCashCriteria borrowCashCriteria);

    /**
     * 根据id获取借款
     * @param borrowId
     * @return
     */
    BorrowCash getById(Long borrowId);

    /**
     * 查询逾期列表
     * @param borrowCashCriteria
     * @return
     */
    ListVo<BorrowCashListVo> getOverdueListByQuery(BorrowCashCriteria borrowCashCriteria);

    /**
     * 逾期减免
     * @param borrowCash
     * @param derateAmount
     * @param credential
     */
    void overdueDerate(BorrowCash borrowCash, BigDecimal derateAmount, OperatorCredential credential) throws TqException;

    /**
     * 获取导出数据
     * @param criteria
     * @return
     */
    List<BorrowCashReportVo> getReportList(FinanceReportCriteria criteria);


    ListVo<UserBorrowListVo> getStageBorrowByUserIdAndState(UserBorrowCriteria criteria);

    /**
     * 查询即将逾期订单
     * @param map
     * @return
     */
    List<BorrowCash> getBeginOverdue(HashMap<String,Object> map);

}
