package com.duobei.core.operation.product.service;

import com.duobei.core.operation.product.domain.Service;
import com.duobei.core.operation.product.domain.vo.ServiceVo;

import java.util.List;

/**服务类型service
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
public interface ServiceService {
    List<ServiceVo> getAll();
}
