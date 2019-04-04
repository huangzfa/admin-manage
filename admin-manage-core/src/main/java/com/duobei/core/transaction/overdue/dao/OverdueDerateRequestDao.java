package com.duobei.core.transaction.overdue.dao;

import com.duobei.core.transaction.overdue.domain.OverdueDerateRequest;
import org.apache.ibatis.annotations.Param;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/15
 */
public interface OverdueDerateRequestDao {
    /**
     * 根据用户id和借款流水号获取待审批的逾期减免
     * @param userId
     * @param borrowNo
     * @return
     */
    OverdueDerateRequest getWaitApproveByUserIdAndBorrowNo(@Param("userId") Long userId,@Param("borrowNo") String borrowNo);
}
