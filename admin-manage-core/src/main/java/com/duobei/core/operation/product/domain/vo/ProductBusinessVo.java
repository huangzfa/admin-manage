package com.duobei.core.operation.product.domain.vo;

import com.duobei.core.operation.product.domain.ProductBusiness;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/31
 */
@Data
public class ProductBusinessVo extends ProductBusiness {
    //业务名称
    private String bizName;
}
