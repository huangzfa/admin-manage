package com.duobei.core.operation.biz.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.criteria.ResourceLogCriteria;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/8
 */
public interface BizResourceLogService {

    ListVo<BizResourceLog> getPage(ResourceLogCriteria criteria);
}
