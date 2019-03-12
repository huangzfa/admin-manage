package com.duobei.core.operation.channel.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.criteria.PromotionChannelStyleCriteria;
import com.duobei.core.operation.channel.domain.vo.PromotionChannelStyleVo;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/7
 */
public interface PromotionChannelStyleService {
    /**
     * 获取列表
     * @param promotionChannelStyleCriteria
     * @return
     */
    ListVo<PromotionChannelStyleVo> getStyleListByQuery(PromotionChannelStyleCriteria promotionChannelStyleCriteria);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    PromotionChannelStyle getById(Integer id);

    /**
     * 删除
     * @param promotionChannelStyle
     */
    void delete(PromotionChannelStyle promotionChannelStyle) throws TqException;

    /**
     * 新增
     * @param promotionChannelStyle
     */
    void addStyle(PromotionChannelStyle promotionChannelStyle) throws TqException;

    void updateStyle(PromotionChannelStyle promotionChannelStyle) throws TqException;

    /**
     * 获取所有在用样式
     * @return
     */
    List<PromotionChannelStyle> getAllList();
}
