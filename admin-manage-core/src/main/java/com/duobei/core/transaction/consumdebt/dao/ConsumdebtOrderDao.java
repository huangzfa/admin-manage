package com.duobei.core.transaction.consumdebt.dao;

import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface ConsumdebtOrderDao {
    ConsumdebtOrder getByUserIdAndBorrowId(@Param("userId") Long userId, @Param("borrowId") Long borrowId);
}
