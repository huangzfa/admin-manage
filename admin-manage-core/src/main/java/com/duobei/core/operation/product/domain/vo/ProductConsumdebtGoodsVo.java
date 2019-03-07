package com.duobei.core.operation.product.domain.vo;

import com.duobei.core.operation.product.domain.ProductConsumdebtGoods;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/7
 */
@Data
public class ProductConsumdebtGoodsVo extends ProductConsumdebtGoods{
    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品编号
     */
    private String goodsNo;

    /**
     *
     */
    private Integer isDelete;

    /**
     *
     */
    private Integer sort;
}
