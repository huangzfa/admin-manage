package com.duobei.core.operation.copywrite.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.copywrite.domain.criteria.CopywritingConfigCriteria;
import com.duobei.core.operation.copywrite.domain.CopywritingConfig;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/20
 */
public interface CopywritingConfigService {
    ListVo<CopywritingConfig> getListByQuery(CopywritingConfigCriteria configCriteria);

    CopywritingConfig getById(Integer id);

    void update(CopywritingConfig entity) throws TqException;
}
