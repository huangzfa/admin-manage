package com.duobei.core.transaction.consumdebt.dao;

import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.domain.criteria.ConsumdebtOrderCriteria;
import com.duobei.core.transaction.consumdebt.domain.vo.ConsumdebtOrderListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface ConsumdebtOrderDao {
    ConsumdebtOrder getByUserIdAndBorrowId(@Param("userId") Long userId, @Param("borrowId") Long borrowId);

    int updateDelivery(ConsumdebtOrder entity);

    List<ConsumdebtOrderListVo> getListByQuery(ConsumdebtOrderCriteria consumdebtOrderCriteria);

    ConsumdebtOrder getById(Long id);

    List<ConsumdebtOrder> getListByReportQuery(ConsumdebtOrderCriteria criteria);
}
