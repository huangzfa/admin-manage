package com.duobei.core.operation.product.domain.vo;

import com.duobei.core.operation.product.domain.ProductAuthConfig;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/4
 */
@Data
public class ProductAuthConfigVo extends ProductAuthConfig {
    private String authName;
}
