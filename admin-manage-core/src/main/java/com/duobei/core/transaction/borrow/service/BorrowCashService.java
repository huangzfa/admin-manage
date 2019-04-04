package com.duobei.core.transaction.borrow.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;

import java.math.BigDecimal;

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
}
