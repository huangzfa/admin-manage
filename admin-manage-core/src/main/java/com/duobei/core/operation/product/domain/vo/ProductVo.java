package com.duobei.core.operation.product.domain.vo;

import com.duobei.core.operation.product.domain.Product;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/28
 */
@Data
public class ProductVo extends Product{
    private String merchantName;

    /**
     * 业务类型
     */
    private String bizCodes;
}
