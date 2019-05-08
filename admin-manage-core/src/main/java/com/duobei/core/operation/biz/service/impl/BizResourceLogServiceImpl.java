package com.duobei.core.operation.biz.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.biz.dao.BizResourceLogDao;
import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.criteria.ResourceLogCriteria;
import com.duobei.core.operation.biz.service.BizResourceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/8
 */
@Service("bizResourceLogService")
public class BizResourceLogServiceImpl implements BizResourceLogService {
    @Autowired
    private BizResourceLogDao resourceLogDao;

    @Override
    public ListVo<BizResourceLog> getPage(ResourceLogCriteria criteria) {
        int total = resourceLogDao.countByCriteria(criteria);
        List<BizResourceLog> list = null;
        if (total > 0) {
            list = resourceLogDao.getPage(criteria);
        }
        return new ListVo<BizResourceLog>(total, list);
    }
}
