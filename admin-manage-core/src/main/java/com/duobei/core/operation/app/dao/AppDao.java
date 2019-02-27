package com.duobei.core.operation.app.dao;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@DataSourceSwitch(dataSource = DataSourceConst.OPERATE)
public interface AppDao {

}