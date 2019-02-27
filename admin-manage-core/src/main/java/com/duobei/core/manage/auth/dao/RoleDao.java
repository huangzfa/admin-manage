package com.duobei.core.manage.auth.dao;

import java.util.List;

import com.duobei.core.manage.auth.domain.criteria.RoleCriteria;
import com.duobei.core.manage.auth.domain.vo.RoleVo;
public interface RoleDao {
    
	List<RoleVo> queryRoleList(RoleCriteria roleCriteria);

	RoleVo queryRoleById(Integer roleId);
}