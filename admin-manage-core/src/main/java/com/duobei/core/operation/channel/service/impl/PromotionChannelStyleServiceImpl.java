package com.duobei.core.operation.channel.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.dao.ProductAppChannelDao;
import com.duobei.core.operation.channel.dao.PromotionChannelStyleDao;
import com.duobei.core.operation.channel.dao.mapper.PromotionChannelStyleMapper;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.PromotionChannelStyleExample;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelStyleCriteria;
import com.duobei.core.operation.channel.domain.vo.ChannelStyleCountVo;
import com.duobei.core.operation.channel.domain.vo.PromotionChannelStyleVo;
import com.duobei.core.operation.channel.service.ProductAppChannelService;
import com.duobei.core.operation.channel.service.PromotionChannelStyleService;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/7
 */
@Service("promotionChannelStyleService")
public class PromotionChannelStyleServiceImpl implements PromotionChannelStyleService {

    @Resource
    PromotionChannelStyleDao promotionChannelStyleDao;

    @Resource
    PromotionChannelStyleMapper promotionChannelStyleMapper;

    @Resource
    ProductAppChannelDao productAppChannelDao;

    @Override
    public ListVo<PromotionChannelStyleVo> getStyleListByQuery(PromotionChannelStyleCriteria promotionChannelStyleCriteria) {
        PromotionChannelStyleExample example = new PromotionChannelStyleExample();
        PromotionChannelStyleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        if (StringUtil.isNotEmpty(promotionChannelStyleCriteria.getStyleName())){
            criteria.andStyleNameEqualTo(promotionChannelStyleCriteria.getStyleName());
        }
        Long total = promotionChannelStyleMapper.countByExample(example);
        //获取集合
        List<PromotionChannelStyleVo> promotionChannelStyleVos = null;
        if (total > 0) {
            promotionChannelStyleVos = promotionChannelStyleDao.queryListVo(promotionChannelStyleCriteria);
            //统计渠道使用量
            Map<Integer,ChannelStyleCountVo> countMap = productAppChannelDao.getCountMapByGroup();
            for (PromotionChannelStyleVo promotionChannelStyleVo : promotionChannelStyleVos){
                ChannelStyleCountVo channelStyleCountVo = countMap.get(promotionChannelStyleVo.getId());
                if (channelStyleCountVo == null){
                    promotionChannelStyleVo.setChannelNum(0);
                }else{
                    promotionChannelStyleVo.setChannelNum(channelStyleCountVo.getCountNum());
                }
            }
        }

        return new ListVo<PromotionChannelStyleVo>(total.intValue(),promotionChannelStyleVos);
    }

    @Override
    public PromotionChannelStyle getById(Integer id) {
        return promotionChannelStyleDao.getById(id);
    }

    @Override
    public void delete(PromotionChannelStyle promotionChannelStyle) throws TqException {
        //删除前，先判断该样式是否在使用，如果存在已使用的配置，则不允许删除
        int count = productAppChannelDao.getCountByStyleId(promotionChannelStyle.getId());
        if (count > 0){
            throw new TqException("该样式被使用中，不允许删除");
        }
        count = promotionChannelStyleDao.deleteById(promotionChannelStyle);
        if (count != 1){
            throw new TqException("删除样式失败");
        }
    }

    @Override
    public void addStyle(PromotionChannelStyle promotionChannelStyle) throws TqException {
        int count = promotionChannelStyleDao.save(promotionChannelStyle);
        if (count != 1){
            throw new TqException("新增样式失败");
        }
    }

    @Override
    public void updateStyle(PromotionChannelStyle promotionChannelStyle) throws TqException {
        int count = promotionChannelStyleDao.update(promotionChannelStyle);
        if (count != 1){
            throw new TqException("修改样式失败");
        }
    }

    @Override
    public List<PromotionChannelStyle> getAllList() {
        return promotionChannelStyleDao.getAllList();
    }
}
