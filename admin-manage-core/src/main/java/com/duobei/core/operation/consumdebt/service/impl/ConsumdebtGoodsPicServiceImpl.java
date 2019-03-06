package com.duobei.core.operation.consumdebt.service.impl;

import com.duobei.core.operation.consumdebt.dao.ConsumdebtGoodsPicDao;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
@Service("ConsumdebtGoodsPicService")
public class ConsumdebtGoodsPicServiceImpl implements ConsumdebtGoodsPicService {

    @Autowired
    private ConsumdebtGoodsPicDao consumdebtGoodsPicDao;

    @Override
    public List<ConsumdebtGoodsPic> getByGoodsId(Integer goodsId){
        return consumdebtGoodsPicDao.getByGoodsId(goodsId);
    }

}
