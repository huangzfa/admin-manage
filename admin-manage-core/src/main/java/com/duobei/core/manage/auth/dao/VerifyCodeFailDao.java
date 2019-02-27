package com.duobei.core.manage.auth.dao;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.core.manage.auth.domain.VerifyCodeFail;

import java.util.HashMap;

/**
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
@DataSourceSwitch(dataSource = DataSourceConst.MANAGE)
public interface VerifyCodeFailDao {

    VerifyCodeFail getByParam(HashMap<String,Object> params);
}
