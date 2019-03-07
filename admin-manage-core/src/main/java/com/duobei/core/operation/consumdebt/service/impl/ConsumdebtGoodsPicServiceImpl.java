package com.duobei.core.operation.consumdebt.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.core.operation.consumdebt.dao.ConsumdebtGoodsPicDao;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     *
     * @param entity
     * @param imgUrls
     * @param type
     * @throws TqException
     */
    @Override
    public void addPic(ConsumdebtGoodsVo entity, String imgUrls,int type)throws TqException{
        String[] split = imgUrls.split(",");
        int sort = 1;
        List<ConsumdebtGoodsPic> list = new ArrayList<>();
        for (String imgUrl : split) {
            ConsumdebtGoodsPic pic = new ConsumdebtGoodsPic();
            pic.setPicUrl(imgUrl);
            pic.setGoodsId(entity.getId());
            pic.setAddOperatorId(entity.getModifyOperatorId());
            pic.setSort(sort);
            pic.setPicType(type);
            sort++;
            list.add(pic);
        }
        consumdebtGoodsPicDao.batchInsert(list);
    }

    /**
     *
     * @param goodsId
     */
    @Override
    public void deleteByGoodsId(Integer goodsId){

        consumdebtGoodsPicDao.deleteByGoodsId(goodsId);
    }
}
