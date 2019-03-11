package com.duobei.core.operation.channel.domain.vo;

import com.duobei.core.operation.channel.domain.ProductAppChannel;
import com.duobei.core.operation.channel.domain.PromotionChannel;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/8
 */
public class ProductAppChannelListVo extends PromotionChannel {

    /**
     * 如果存在配置，则存在此对象信息
     */
    private ProductAppChannel productAppChannel;

    public ProductAppChannel getProductAppChannel() {
        return productAppChannel;
    }

    public void setProductAppChannel(ProductAppChannel productAppChannel) {
        this.productAppChannel = productAppChannel;
    }
}
