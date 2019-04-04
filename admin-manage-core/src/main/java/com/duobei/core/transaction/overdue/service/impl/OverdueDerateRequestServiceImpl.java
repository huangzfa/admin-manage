package com.duobei.core.transaction.overdue.service.impl;

import com.duobei.core.transaction.overdue.dao.OverdueDerateRequestDao;
import com.duobei.core.transaction.overdue.dao.mapper.OverdueDerateRequestMapper;
import com.duobei.core.transaction.overdue.domain.OverdueDerateRequest;
import com.duobei.core.transaction.overdue.service.OverdueDerateRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/15
 */
@Service("overdueDerateRequestService")
public class OverdueDerateRequestServiceImpl implements OverdueDerateRequestService {
    @Resource
    OverdueDerateRequestMapper overdueDerateRequestMapper;

    @Resource
    OverdueDerateRequestDao overdueDerateRequestDao;

    @Override
    public OverdueDerateRequest getWaitApproveByUserIdAndBorrowNo(Long userId, String borrowNo) {
        return overdueDerateRequestDao.getWaitApproveByUserIdAndBorrowNo(userId,borrowNo);
    }
}
