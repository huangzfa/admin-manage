package com.duobei.core.operation.biz.dao;

import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.criteria.ResourceLogCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/8
 */
public interface BizResourceLogDao {

    List<BizResourceLog> getPage(ResourceLogCriteria criteria);

    int countByCriteria(ResourceLogCriteria ResourceCriteria);

    BizResourceLog getById(Integer id);

    int save(BizResourceLog entity);

    int update(BizResourceLog entity);
}
