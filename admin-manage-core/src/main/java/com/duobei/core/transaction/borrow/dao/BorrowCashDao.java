package com.duobei.core.transaction.borrow.dao;

import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashReportVo;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.user.user.domain.criteria.UserBorrowCriteria;
import com.duobei.core.user.user.domain.vo.UserBorrowListVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
public interface BorrowCashDao {
    List<BorrowCashListVo> getListVoByQuery(BorrowCashCriteria borrowCashCriteria);

    BorrowCash getById(Long borrowId);

    /**
     * 根据借款编号查询借款ID
     * @param borrowNo
     * @return
     */
    List<Long> getIdByBorrowNo(String borrowNo);

    /**
     * 根据还款的借款ID List 查询借款Map
     * @param data
     * @return
     */
    @MapKey("id")
    Map<Long,BorrowCash> getBorrowCashMapByRepaymentBorrowIds(@Param("repaymentData") List<BorrowCashRepaymentListVo> data);

    /**
     * 根据借款编号和用户id查询借款ID
     * @param borrowNo
     * @param userId
     * @return
     */
    List<Long> getIdByBorrowNoAndUserId(@Param("borrowNo") String borrowNo, @Param("userId") Long userId);

    /**
     * 根据还款的续借ID List 查询借款Map
     * @param data
     * @return
     */
    @MapKey("id")
    Map<Long,BorrowCash> getBorrowCashMapByRenewalBorrowIds(@Param("renewalData") List<BorrowCashRenewalListVo> data);

    /**
     * 查询待还款且逾期状态订单
     * @param borrowCashCriteria
     * @return
     */
    List<BorrowCashListVo> getOverdueListVoByQuery(BorrowCashCriteria borrowCashCriteria);

    int overdueAmountDerate(BorrowCash borrowCash);

    List<BorrowCashReportVo> getReportList(FinanceReportCriteria criteria);

    List<UserBorrowListVo> getStageBorrowByUserIdAndState(UserBorrowCriteria criteria);

    /**
     * 根据优惠券id查询借款订单
     * @param couponIds
     * @return
     */
    List<BorrowCash> getByCouponIds(@Param("couponIds") List<HashMap<String,Object>> couponIds);

    /**
     *
     * @param map{borrowNo:userId}
     * @return
     */
    BorrowCash getByBorrowNoMap(HashMap<String,Object> map);
}
