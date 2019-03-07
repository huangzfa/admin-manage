package com.duobei.core.operation.consumdebt.dao;

import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumdebtGoodsPicDao {
    /**
     *
     * @param goodsId
     * @return
     */
    List<ConsumdebtGoodsPic> getByGoodsId(@Param("goodsId") Integer goodsId);

    void deleteByGoodsId(@Param("goodsId") Integer goodsId);

    void batchInsert(@Param("list") List<ConsumdebtGoodsPic> list);
}
