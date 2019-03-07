package com.duobei.core.operation.consumdebt.service;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;

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

    /**
     *
     * @param entity
     * @param imgUrls
     * @param type
     * @throws TqException
     */
    void addPic(ConsumdebtGoodsVo entity, String imgUrls,int type)throws TqException;

    /**
     *
     * @param goodsId
     */
    void deleteByGoodsId(Integer goodsId);
}
