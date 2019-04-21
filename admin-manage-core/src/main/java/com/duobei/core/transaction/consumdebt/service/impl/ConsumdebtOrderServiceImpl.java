package com.duobei.core.transaction.consumdebt.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.consumdebt.dao.ConsumdebtOrderDao;
import com.duobei.core.transaction.consumdebt.dao.mapper.ConsumdebtOrderMapper;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrderExample;
import com.duobei.core.transaction.consumdebt.domain.criteria.ConsumdebtOrderCriteria;
import com.duobei.core.transaction.consumdebt.domain.vo.ConsumdebtOrderListVo;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("consumdebtOrderService")
public class ConsumdebtOrderServiceImpl implements ConsumdebtOrderService {
   @Resource
    ConsumdebtOrderDao consumdebtOrderDao;

   @Resource
    ConsumdebtOrderMapper consumdebtOrderMapper;

    @Resource
    ProductDao productDao;
    @Override
    public ConsumdebtOrder getByUserIdAndBorrowId(Long userId, Long borrowId) {
        return consumdebtOrderDao.getByUserIdAndBorrowId(userId,borrowId);
    }

    @Override
    public ListVo<ConsumdebtOrderListVo> getListByQuery(ConsumdebtOrderCriteria consumdebtOrderCriteria) {
        //查询产品信息
        Product product = productDao.getById(consumdebtOrderCriteria.getProductId());
        Long total = queryCount(consumdebtOrderCriteria);
        //如果数量>0则查询列表
        List<ConsumdebtOrderListVo> data = null;
        if (total > 0 ){
            data = consumdebtOrderDao.getListByQuery(consumdebtOrderCriteria);
            for (ConsumdebtOrderListVo vo : data){
                vo.setProductName(product.getProductName());
            }
        }
        return new ListVo<ConsumdebtOrderListVo>(total.intValue(),data);
    }

    @Override
    public ConsumdebtOrder getById(Long id) {
        return consumdebtOrderDao.getById(id);
    }

    @Override
    public void updateDelivery(ConsumdebtOrder entity) throws TqException {
        int count = consumdebtOrderDao.updateDelivery(entity);
        if (count != 1){
            throw new TqException("修改失败,请刷新后重试");
        }
    }

    @Override
    public Long queryCount(ConsumdebtOrderCriteria consumdebtOrderCriteria) {
        ConsumdebtOrderExample example = new ConsumdebtOrderExample();
        ConsumdebtOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        //搜索条件封装
        if (StringUtil.isNotEmpty(consumdebtOrderCriteria.getOrderNo())){
            criteria.andOrderNoEqualTo(consumdebtOrderCriteria.getOrderNo());
        }
        if (consumdebtOrderCriteria.getProductId() != null){
            criteria.andProductIdEqualTo(consumdebtOrderCriteria.getProductId());
        }
        if (consumdebtOrderCriteria.getUserId() != null){
            criteria.andUserIdEqualTo(consumdebtOrderCriteria.getUserId());
        }
        if (StringUtil.isNotEmpty(consumdebtOrderCriteria.getLogisticsNo())){
            criteria.andLogisticsNoEqualTo(consumdebtOrderCriteria.getLogisticsNo());
        }
        if (consumdebtOrderCriteria.getEndTime() != null && consumdebtOrderCriteria.getStartTime() != null){
            //存在创建开始和结束时间条件查询
            criteria.andAddTimeBetween(consumdebtOrderCriteria.getStartTime(),consumdebtOrderCriteria.getEndTime());
        }else if(consumdebtOrderCriteria.getEndTime() != null && consumdebtOrderCriteria.getStartTime() == null){
            //存在创建结束时间条件查询
            criteria.andAddTimeLessThan(consumdebtOrderCriteria.getEndTime());
        }else if(consumdebtOrderCriteria.getEndTime() == null && consumdebtOrderCriteria.getStartTime() != null){
            //存在创建开始时间条件查询
            criteria.andAddTimeGreaterThan(consumdebtOrderCriteria.getStartTime());
        }
        if(consumdebtOrderCriteria.getState() != null){
            criteria.andStateEqualTo(consumdebtOrderCriteria.getState());
        }
        Long total = consumdebtOrderMapper.countByExample(example);
        return total;
    }

    @Override
    public List<ConsumdebtOrder> getListByReportQuery(ConsumdebtOrderCriteria criteria) {
        return consumdebtOrderDao.getListByReportQuery(criteria);
    }
}
