package com.duobei.core.operation.product.dao;

import com.duobei.core.operation.product.domain.vo.BusinessVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/5
 */
public interface BusinessDao {

    List<BusinessVo> getAll();
}
