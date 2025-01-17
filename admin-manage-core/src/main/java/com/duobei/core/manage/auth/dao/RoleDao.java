package com.duobei.core.manage.auth.dao;

import java.util.List;

import com.duobei.core.manage.auth.domain.criteria.RoleCriteria;
import com.duobei.core.manage.auth.domain.vo.RoleVo;
public interface RoleDao {

	int count(RoleCriteria roleCriteria);

	List<RoleVo> queryRoleList(RoleCriteria roleCriteria);

	RoleVo queryRoleById(Integer roleId);

	int countByRoleName(RoleVo role);
}