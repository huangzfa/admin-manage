package com.duobei.core.operation.channel.service.impl;

import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.dao.ProductAppChannelDao;
import com.duobei.core.operation.channel.dao.PromotionChannelDao;
import com.duobei.core.operation.channel.dao.mapper.PromotionChannelMapper;
import com.duobei.core.operation.channel.domain.ProductAppChannel;
import com.duobei.core.operation.channel.domain.PromotionChannelExample;
import com.duobei.core.operation.channel.domain.criteria.ProductAppChannelCriteria;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;
import com.duobei.core.operation.channel.service.ProductAppChannelService;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description 产品应用推广渠道信息
 * @date 2019/2/26
 */
@Service("productAppChannelService")
public class ProductAppChannelServiceImpl implements ProductAppChannelService{
    @Resource
    PromotionChannelDao promotionChannelDao;
    @Resource
    PromotionChannelMapper promotionChannelMapper;


    @Resource
    ProductAppChannelDao productAppChannelDao;

    @Override
    public ListVo<ProductAppChannelListVo> getListByQuery(ProductAppChannelCriteria productAppChannelCriteria) {
        //向产品妥协，先查出当前页可用渠道，然后根据渠道去查询是否存在配置，如果存在配置，则显示注册链接等信息，不存在，则显示为空
        PromotionChannelExample example = new PromotionChannelExample();
        PromotionChannelExample.Criteria criteria = example.createCriteria();
        //统计
        criteria.andIsDeleteEqualTo(0);
        if (StringUtil.isNotEmpty(productAppChannelCriteria.getChannelCode())){
            criteria.andChannelCodeEqualTo(productAppChannelCriteria.getChannelCode());
        }
        if (StringUtil.isNotEmpty(productAppChannelCriteria.getChannelName())){
            criteria.andChannelNameEqualTo(productAppChannelCriteria.getChannelName());
        }
        Long total = promotionChannelMapper.countByExample(example);

        List<ProductAppChannelListVo> data = null;
        //如果统计>0 进行下一步查询
        if (total > 0){
            //查询渠道
            data = promotionChannelDao.getChannelByProductAppChannelQuery(productAppChannelCriteria);
            //查询配置
            Map<Integer,ProductAppChannel> dataMap = productAppChannelDao.getListByChannelIdAndAppId(data,productAppChannelCriteria.getAppId());
            for (ProductAppChannelListVo productAppChannelListVo:data){
                //赋值配置数据
                productAppChannelListVo.setProductAppChannel(dataMap.get(productAppChannelListVo.getId()));
            }
        }
        return new ListVo(total.intValue(),data);
    }
}
