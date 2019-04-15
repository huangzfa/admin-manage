package com.duobei.core.operation.biz.service;

import com.duobei.core.operation.biz.domain.BizResource;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
public interface BizResourceService {

    BizResource getByResTypeAnResTypeSec(String resType,String resTypeSec);
}
