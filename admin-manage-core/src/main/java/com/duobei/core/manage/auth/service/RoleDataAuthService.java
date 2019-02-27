package com.duobei.core.manage.auth.service;

import com.duobei.core.manage.auth.domain.vo.RoleDataAuthVo;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
public interface RoleDataAuthService {

    /**
     * 根据角色查询产品
     * @param roleId
     * @return
     */
    List<RoleDataAuthVo> getByRoleId(Integer roleId);
}
