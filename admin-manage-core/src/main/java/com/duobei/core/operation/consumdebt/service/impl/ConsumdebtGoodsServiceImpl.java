package com.duobei.core.operation.consumdebt.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.consumdebt.dao.ConsumdebtGoodsDao;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import com.duobei.core.operation.consumdebt.domain.criteria.ConsumdebtGoodsCriteria;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
@Service("ConsumdebtGoodsService")
public class ConsumdebtGoodsServiceImpl implements ConsumdebtGoodsService {
    @Autowired
    private ConsumdebtGoodsDao consumdebtGoodsDao;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<ConsumdebtGoodsVo> getPageList(ConsumdebtGoodsCriteria criteria) {
        int total = consumdebtGoodsDao.countByCriteria(criteria);
        List<ConsumdebtGoodsVo> list = null;
        if (total > 0) {
            list = consumdebtGoodsDao.getPageList(criteria);
        }
        return new ListVo<ConsumdebtGoodsVo>(total, list);
    }
    @Override
    public void save(ConsumdebtGoodsVo entity) throws TqException{

    }
    @Override
    public void update(ConsumdebtGoodsVo entity) throws TqException{

    }

    /**
     *
     * @param goodsNo
     * @return
     */
    @Override
    public ConsumdebtGoodsVo getByGoodsNo(String goodsNo){
        return consumdebtGoodsDao.getByGoodsNo(goodsNo);
    }
}
