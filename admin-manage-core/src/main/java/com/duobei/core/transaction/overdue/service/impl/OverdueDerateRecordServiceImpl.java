package com.duobei.core.transaction.overdue.service.impl;

import com.duobei.core.transaction.overdue.dao.OverdueDerateRecordDao;
import com.duobei.core.transaction.overdue.dao.mapper.OverdueDerateRecordMapper;
import com.duobei.core.transaction.overdue.service.OverdueDerateRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/15
 */
@Service("overdueDerateRecordService")
public class OverdueDerateRecordServiceImpl implements OverdueDerateRecordService {
    @Resource
    OverdueDerateRecordMapper overdueDerateRecordMapper;

    @Resource
    OverdueDerateRecordDao overdueDerateRecordDao;
}
