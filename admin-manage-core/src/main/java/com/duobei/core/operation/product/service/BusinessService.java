package com.duobei.core.operation.product.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.domain.Business;
import com.duobei.core.operation.product.domain.criteria.BusinessCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/5
 */
public interface BusinessService {
    /**
     *
     * @return
     */
    List<BusinessVo> getAll();

    ListVo<BusinessVo> getPageList();
}
