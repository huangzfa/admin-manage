package com.duobei.core.operation.product.domain.vo;

import com.duobei.core.operation.product.domain.Business;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/5
 */
@Data
public class BusinessVo extends Business {
    /**
     * 是否选中
     */
    private String checked;

    /**
     * 关联的服务类型
     */
    private String serviceName;
}
