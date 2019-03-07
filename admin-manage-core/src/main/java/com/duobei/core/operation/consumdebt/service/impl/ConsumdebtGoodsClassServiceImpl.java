package com.duobei.core.operation.consumdebt.service.impl;

import com.duobei.core.operation.consumdebt.dao.ConsumdebtGoodsClassDao;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsClass;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/7
 */
@Service("ConsumdebtGoodsClassService")
public class ConsumdebtGoodsClassServiceImpl implements ConsumdebtGoodsClassService {

    @Autowired
    private ConsumdebtGoodsClassDao consumdebtGoodsClassDao;

    /**
     *
     * @return
     */
    @Override
    public List<ConsumdebtGoodsClass> getAll(){
        return consumdebtGoodsClassDao.getAll();
    }
}
