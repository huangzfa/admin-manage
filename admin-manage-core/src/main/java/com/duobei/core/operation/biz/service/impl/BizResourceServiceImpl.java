package com.duobei.core.operation.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.biz.dao.BizResourceDao;
import com.duobei.core.operation.biz.dao.BizResourceLogDao;
import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.criteria.BizResourceCriteria;
import com.duobei.core.operation.biz.service.BizResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/11
 */
@Service(value = "bizResourceService")
public class BizResourceServiceImpl implements BizResourceService {

    @Resource
    BizResourceDao bizResourceDao;
    @Autowired
    private BizResourceLogDao resourceLogDao;

    @Override
    public BizResource getByResTypeAnResTypeSec(String resType, String resTypeSec) {
        return bizResourceDao.getByResTypeAnResTypeSec(resType,resTypeSec);
    }

    /**
     * 分页查询
     *
     * @param criteria
     * @return
     */
    @Override
    public ListVo<BizResource> getPage(BizResourceCriteria criteria) {
        int total = bizResourceDao.countByCriteria(criteria);
        List<BizResource> list = null;
        if (total > 0) {
            list = bizResourceDao.getPage(criteria);
        }
        return new ListVo<BizResource>(total, list);
    }

    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void save(BizResource entity) throws TqException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("resType",entity.getResType());
        map.put("resTypeSec",entity.getResTypeSec());
        map.put("appId",entity.getAppId());
        map.put("productId",entity.getProductId());
        int count = bizResourceDao.countByType(map);
        if ( count > 0) {
            throw new TqException("该资源类型已存在");
        }
        int result = bizResourceDao.save(entity);
        if (result != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
        BizResourceLog log = new BizResourceLog()
                .setResType(entity.getResType())
                .setResTypeSec(entity.getResTypeSec())
                .setAddOperatorId(entity.getAddOperatorId())
                .setProductId(entity.getProductId())
                .setModifyJson(JSON.toJSONString(entity))
                .setOldJson("")
                .setAddOperatorId(entity.getModifyOperatorId());
        if (resourceLogDao.save(log) != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
    }

    @Override
    public BizResource getById(Integer id) {
        return bizResourceDao.getById(id);
    }

    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void update(BizResource entity) throws TqException {
        BizResource entity1 = bizResourceDao.getById(entity.getId());
        if( entity1 == null){
            throw new TqException("该资源不存在");
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("resType",entity.getResType());
        map.put("resTypeSec",entity.getResTypeSec());
        map.put("appId",entity.getAppId());
        map.put("appId",entity.getProductId());
        map.put("id",entity.getId());
        int count = bizResourceDao.countByType(map);
        if (entity.getIsDelete() == null && count>0) {
            throw new TqException("该资源类型已存在");
        }
        BizResourceLog log = new BizResourceLog()
                .setResType(entity.getResType())
                .setResTypeSec(entity.getResTypeSec())
                .setAddOperatorId(entity.getAddOperatorId())
                .setProductId(entity.getProductId())
                .setModifyJson(JSON.toJSONString(entity))
                .setOldJson(JSON.toJSONString(entity1))
                .setAddOperatorId(entity.getModifyOperatorId());
        if (resourceLogDao.save(log) != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
        int result = bizResourceDao.update(entity);
        if (result != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
    }

    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void delete(BizResource entity) throws TqException{
        BizResourceLog log = new BizResourceLog()
                .setResType(entity.getResType())
                .setResTypeSec(entity.getResTypeSec())
                .setAddOperatorId(entity.getAddOperatorId())
                .setProductId(entity.getProductId())
                .setModifyJson("")
                .setOldJson(JSON.toJSONString(entity))
                .setAddOperatorId(entity.getModifyOperatorId());
        if (resourceLogDao.save(log) != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
        int result = bizResourceDao.update(entity);
        if (result != 1) {
            throw new TqException("系统异常,资源配置错误");
        }
    }
}
