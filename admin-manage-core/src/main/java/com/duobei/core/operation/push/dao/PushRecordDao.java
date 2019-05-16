package com.duobei.core.operation.push.dao;

import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
public interface PushRecordDao {
    List<PushRecordVo> getPage(PushRecordCriteria criteria);

    int countByCriteria(PushRecordCriteria criteria);

    int save(PushRecord record);

    int update(PushRecord record);

    PushRecord getById(Long id);
}
