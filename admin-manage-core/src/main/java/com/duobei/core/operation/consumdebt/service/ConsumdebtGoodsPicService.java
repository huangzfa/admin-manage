package com.duobei.core.operation.consumdebt.service;

import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
public interface ConsumdebtGoodsPicService {
    /**
     * 根据商品id查询图片
     * @param goodsId
     * @return
     */
    List<ConsumdebtGoodsPic> getByGoodsId(Integer goodsId);
}
