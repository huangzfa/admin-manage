package com.duobei.core.operation.copywrite.dao;

import com.duobei.core.operation.copywrite.domain.criteria.CopywritingConfigCriteria;
import com.duobei.core.operation.copywrite.domain.CopywritingConfig;

import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/20
 */
public interface CopywritingConfigDao {
    List<CopywritingConfig> getListByQuery(CopywritingConfigCriteria configCriteria);

    int update(CopywritingConfig entity);

    CopywritingConfig getById(Integer id);
}
