package com.duobei.core.operation.product.dao;

import com.duobei.core.operation.product.domain.vo.ServiceVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
public interface ServiceDao {
    List<ServiceVo> getAll();
}
