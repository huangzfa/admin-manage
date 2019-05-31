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

    /**
     * 根据订单号获取订单信息
     * @param value
     * @return
     */
    ConsumdebtOrder getByOrderNo(String value);

    int deliveryConsumdebtOrder(ConsumdebtOrder consumdebtOrderDo);

    /**
     * 根据订单号查询
     * @param orderNos
     * @return
     */
    List<ConsumdebtOrder> getByOrderNos(@Param("list") List<String> orderNos);

    /**
     * 批量发货
     * @param orderNos
     * @return
     */
    int batchDeliver(@Param("list") List<String> orderNos);
}
