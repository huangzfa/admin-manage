package com.duobei.core.operation.biz.service.impl;

import com.duobei.core.operation.biz.dao.BizResourceDao;
import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.service.BizResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
@Service(value = "bizResourceService")
public class BizResourceServiceImpl implements BizResourceService {

    @Resource
    BizResourceDao bizResourceDao;
    @Override
    public BizResource getByResTypeAnResTypeSec(String resType, String resTypeSec) {
        return bizResourceDao.getByResTypeAnResTypeSec(resType,resTypeSec);
    }
}
