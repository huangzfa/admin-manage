package com.duobei.core.app.dao;

import com.duobei.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.core.app.domain.criteria.AppExamineCriteria;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@DataSourceSwitch(dataSource = DataSourceConst.OPERATE)
public interface AppExamineDao {

    /**
     * 总条数
     * @param criteria
     * @return
     */
    long countCriteria (AppExamineCriteria criteria);

    /**
     * list数据
     * @param criteria
     * @return
     */
    List queryAppList(AppExamineCriteria criteria);
}
