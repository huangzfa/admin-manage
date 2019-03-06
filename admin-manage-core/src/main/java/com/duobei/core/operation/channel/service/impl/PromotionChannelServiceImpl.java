package com.duobei.core.operation.channel.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.dao.PromotionChannelDao;
import com.duobei.core.operation.channel.dao.mapper.PromotionChannelMapper;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.PromotionChannelExample;
import com.duobei.core.operation.channel.domain.PromotionChannelExample.Criteria;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelCriteria;
import com.duobei.core.operation.channel.service.PromotionChannelService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/5
 */
@Service("promotionChannelService")
public class PromotionChannelServiceImpl implements PromotionChannelService {
    @Resource
    PromotionChannelDao promotionChannelDao;

    @Resource
    PromotionChannelMapper promotionChannelMapper;

    @Override
    public PromotionChannel getById(Integer id) {
        return promotionChannelDao.getById(id);
    }

    @Override
    public ListVo<PromotionChannel> getListByQuery(PromotionChannelCriteria promotionChannelCriteria) {
        //统计
        int total = promotionChannelDao.countByQuery(promotionChannelCriteria);

        if (total > 0){
            //查询
            List<PromotionChannel> data = promotionChannelDao.getListByQuery();
            return new ListVo<PromotionChannel>(total,data);
        }else{
            return new ListVo<PromotionChannel>(total,null);
        }
    }

    @Override
    public void addPromotionChannel(PromotionChannel promotionChannel) throws TqException {
        int count = promotionChannelDao.sava(promotionChannel);
        if (count != 1){
            throw new TqException("渠道新增失败");
        }
    }

    @Override
    public void updatePromotionChannel(PromotionChannel promotionChannel) throws TqException {
        int count = promotionChannelDao.update(promotionChannel);
        if (count != 1){
            throw new TqException("渠道修改失败");
        }
    }

    @Override
    public void delete(PromotionChannel promotionChannel) throws TqException {
        int count = promotionChannelDao.delete(promotionChannel);
        if (count != 1){
            throw new TqException("渠道删除失败");
        }
    }
}
