package com.duobei.core.transaction.consumdebt.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.domain.criteria.ConsumdebtOrderCriteria;
import com.duobei.core.transaction.consumdebt.domain.vo.ConsumdebtOrderListVo;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
public interface ConsumdebtOrderService {
    ConsumdebtOrder getByUserIdAndBorrowId(Long userId, Long borrowId);

    /**
     * 查询借贷商品订单
     * @param consumdebtOrderCriteria
     * @return
     */
    ListVo<ConsumdebtOrderListVo> getListByQuery(ConsumdebtOrderCriteria consumdebtOrderCriteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ConsumdebtOrder getById(Long id);

    /**
     * 修改发货信息
     * @param entity
     */
    void updateDelivery(ConsumdebtOrder entity) throws TqException;

    /**
     * 统计总数
     * @param consumdebtOrderCriteria
     * @return
     */
    Long queryCount(ConsumdebtOrderCriteria consumdebtOrderCriteria);

    /**
     * 获取导出数据
     * @param criteria
     * @return
     */
    List<ConsumdebtOrder> getListByReportQuery(ConsumdebtOrderCriteria criteria);

   /* *//**
     * 批量导入发货数据
     * @param filePath
     * @return
     *//*
    BatchDeliveryResult batchDeliveryConsumdebtOrder(String filePath);*/
}
