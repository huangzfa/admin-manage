package com.duobei.core.transaction.overdue.service;

import com.duobei.core.transaction.overdue.domain.OverdueDerateRequest;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/15
 */
public interface OverdueDerateRequestService {
    OverdueDerateRequest getWaitApproveByUserIdAndBorrowNo(Long userId, String borrowNo);
}
