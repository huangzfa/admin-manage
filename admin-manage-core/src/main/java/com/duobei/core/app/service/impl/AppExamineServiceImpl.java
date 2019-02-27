package com.duobei.core.app.service.impl;

import com.duobei.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.common.datasource.DataSourceHandle;
import com.duobei.common.vo.ListVo;
import com.duobei.core.app.dao.AppExamineDao;
import com.duobei.core.app.domain.AppExamine;
import com.duobei.core.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.app.service.AppExamineService;
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
    @DataSourceSwitch(dataSource = DataSourceConst.OPERATE)
    public ListVo<AppExamine> getPageList(AppExamineCriteria criteria){
        //1
        Long total = appExamineDao.countCriteria(criteria);
        List<AppExamine> appExamineVos = null;
        if (total > 0) {
            appExamineVos = appExamineDao.queryAppList(criteria);
        }
        //2
        return new ListVo<AppExamine>(total.intValue(),appExamineVos);
    }


}
