package com.duobei.core.operation.push.service;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.common.vo.ReturnParamsVo;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;

import java.util.List;

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

    /**
     * 立即推送
     * @param record
     * @throws TqException
     */
    void save(PushRecord record,List<List<Object>> listob) throws TqException;

    /**
     * 定时推送
     * @param record
     * @return
     */
    ReturnParamsVo saveQuartzInfo(PushRecord record,List<List<Object>> listob) ;

    /**
     * 推送给极光
     * @param record
     * @param listob
     * @return
     */
    ReturnParamsVo jgPush(PushRecord record,List<List<Object>> listob);

    /**
     * 短信推送
     * @param record
     * @param listob
     * @return
     */
    ReturnParamsVo smsPush(PushRecord record,List<List<Object>> listob);

    PushRecord getById(Long id);

    void update(PushRecord record) throws TqException;
}
