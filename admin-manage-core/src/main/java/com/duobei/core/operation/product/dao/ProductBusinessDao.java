package com.duobei.core.operation.product.dao;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;

/**
 * @author huangzhongfa
 * @description 产品业务表
 * @date 2019/2/26
 */
@DataSourceSwitch(dataSource = DataSourceConst.OPERATE)
public interface ProductBusinessDao {
}
