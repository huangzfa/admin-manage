package com.duobei.core.operation.app.service.impl;

import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.app.dao.AppExamineDao;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.service.AppExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Service("AppExamineService")
public class AppExamineServiceImpl implements AppExamineService {

    @Autowired
    private AppExamineDao appExamineDao;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<AppExamine> getPageList(AppExamineCriteria criteria){

        Long total = appExamineDao.countCriteria(criteria);
        List<AppExamine> appExamineVos = null;
        if (total > 0) {
            appExamineVos = appExamineDao.queryAppList(criteria);
        }

        return new ListVo<AppExamine>(total.intValue(),appExamineVos);
    }
}
