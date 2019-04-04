package com.duobei.core.transaction.consumdebt.service.impl;

import com.duobei.core.transaction.consumdebt.dao.ConsumdebtOrderDao;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("consumdebtOrderService")
public class ConsumdebtOrderServiceImpl implements ConsumdebtOrderService {
   @Resource
    ConsumdebtOrderDao consumdebtOrderDao;

    @Override
    public ConsumdebtOrder getByUserIdAndBorrowId(Long userId, Long borrowId) {
        return consumdebtOrderDao.getByUserIdAndBorrowId(userId,borrowId);
    }
}
