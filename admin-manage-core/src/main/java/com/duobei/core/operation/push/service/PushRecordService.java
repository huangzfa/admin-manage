package com.duobei.core.operation.push.service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
public interface PushRecordService {
    /**
     * 分页查询
     * @param criteria
     * @return
     */
    ListVo<PushRecordVo> getPageList(PushRecordCriteria criteria);
}
