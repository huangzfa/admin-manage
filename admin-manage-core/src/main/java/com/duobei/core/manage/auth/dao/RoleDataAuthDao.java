package com.duobei.core.manage.auth.dao;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.core.manage.auth.domain.RoleDataAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@DataSourceSwitch(dataSource = DataSourceConst.MANAGE)
public interface RoleDataAuthDao {

    /**
     * 根据角色查询产品
     * @param roleId
     * @return
     */
    List<RoleDataAuth> getByRoleId(@Param("roleId") Integer roleId);
}
