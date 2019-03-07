package com.duobei.core.operation.consumdebt.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import com.duobei.core.operation.consumdebt.domain.criteria.ConsumdebtGoodsCriteria;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
public interface ConsumdebtGoodsService {

    /**
     * 分页查询
     *
     * @param criteria
     * @return
     */
    ListVo<ConsumdebtGoodsVo> getPageList(ConsumdebtGoodsCriteria criteria);

    /**
     *
     * @param entity
     * @throws TqException
     */
    void saveOrUpdate(ConsumdebtGoodsVo entity) throws TqException;

    /**
     *
     * @param goodsNo
     * @return
     */
    ConsumdebtGoodsVo getByGoodsNo(String goodsNo);

    /**
     * 
     * @param productId
     * @return
     */
    List<ConsumdebtGoodsVo> getList(Integer productId);
}
