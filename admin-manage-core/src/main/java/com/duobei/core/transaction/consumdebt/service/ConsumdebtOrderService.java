package com.duobei.core.transaction.consumdebt.service;

import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface ConsumdebtOrderService {
    ConsumdebtOrder getByUserIdAndBorrowId(Long userId, Long borrowId);
}
