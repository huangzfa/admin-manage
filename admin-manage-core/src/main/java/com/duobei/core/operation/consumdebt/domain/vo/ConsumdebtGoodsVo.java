package com.duobei.core.operation.consumdebt.domain.vo;

import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
@Data
public class ConsumdebtGoodsVo extends ConsumdebtGoods{
    /**
     * 商品价格，分
     */
    private Double priceAmountDouble;

    /**
     * 优惠价，分
     */
    private Double saleAmountDouble;

    /**
     * 下限金额(含)，分
     */
    private Double minAmountDouble;

    /**
     * 上限金额(不含)，分
     */
    private Double maxAmountDouble;

    private String detailUrls;

    private String bannerUrls;
}
