package com.duobei.core.operation.channel.dao;

import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelStyleCriteria;
import com.duobei.core.operation.channel.domain.vo.PromotionChannelStyleVo;
import java.util.List;

import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/7
 */
public interface PromotionChannelStyleDao {
    List<PromotionChannelStyleVo> queryListVo(PromotionChannelStyleCriteria promotionChannelStyleCriteria);

    PromotionChannelStyle getById(Integer id);

    int deleteById(PromotionChannelStyle promotionChannelStyle);

    int save(PromotionChannelStyle promotionChannelStyle);

    int update(PromotionChannelStyle promotionChannelStyle);

    List<PromotionChannelStyle> getAllList();
}
