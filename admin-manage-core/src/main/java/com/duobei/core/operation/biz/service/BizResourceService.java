package com.duobei.core.operation.biz.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.domain.criteria.BizResourceCriteria;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface BizResourceService {

    BizResource getByResTypeAnResTypeSec(String resType,String resTypeSec);

    /**
     * 分页查询
     * @param ResourceCriteria
     * @return
     */
    ListVo<BizResource> getPage(BizResourceCriteria ResourceCriteria);

    BizResource getById(Integer id);

    void save(BizResource entity) throws TqException;

    void update(BizResource entity) throws TqException;

    void delete(BizResource entity) throws TqException;
}
