package com.duobei.core.operation.consumdebt.dao;

import com.duobei.core.operation.consumdebt.domain.criteria.ConsumdebtGoodsCriteria;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
public interface ConsumdebtGoodsDao {
    /**
     *分页查询
     * @param criteria
     * @return
     */
    List<ConsumdebtGoodsVo> getPageList(ConsumdebtGoodsCriteria criteria);

    /**
     *分页查询求total数量
     * @param criteria
     * @return
     */
    int countByCriteria(ConsumdebtGoodsCriteria criteria);

    /**
     *
     * @param goodsNo
     * @return
     */
    ConsumdebtGoodsVo getByGoodsNo(@Param("goodsNo") String goodsNo);

    /**
     *
     * @param entity
     * @return
     */
    int save(ConsumdebtGoodsVo entity);

    /**
     *
     * @param update
     * @return
     */
    int update (ConsumdebtGoodsVo update);

    List<ConsumdebtGoodsVo> getAll();
}
