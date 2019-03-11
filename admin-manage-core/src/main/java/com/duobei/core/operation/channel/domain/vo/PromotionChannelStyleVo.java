package com.duobei.core.operation.channel.domain.vo;

import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import lombok.Data;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/7
 */

public class PromotionChannelStyleVo extends PromotionChannelStyle {
    private Integer channelNum;

    public Integer getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }
}
