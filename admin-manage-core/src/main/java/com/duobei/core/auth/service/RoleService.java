package com.duobei.core.auth.service;

import java.util.List;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.RoleMenuKey;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.RoleCriteria;
import com.duobei.core.auth.domain.vo.RoleVo;
import com.duobei.common.exception.TqException;

public interface RoleService {

	/**
	 * 查询角色列表-分页及条件
	 * @param credential
	 * @param roleCriteria
	 * @return
	 * @throws TqException
	 */
	ListVo<RoleVo> queryRoleList(OperatorCredential credential,RoleCriteria roleCriteria)throws TqException;
	/**
	 * 查询角色
	 * @param roleId
	 * @return
	 * @throws TqException
	 */
	RoleVo queryRoleById(Integer roleId)throws TqException;
	/**
	 * 查询角色已经授权的菜单权限
	 * @param roleId
	 * @return
	 */
	List<RoleMenuKey> queryRoleMenuByRoleId(Integer roleId);
	/**
	 * 添加角色
	 * @param credential
	 * @param role
	 * @throws TqException
	 */
	void addRole(OperatorCredential credential,RoleVo role) throws TqException;
	/**
	 * 更新角色
	 * @param credential
	 * @param role
	 * @throws TqException
	 */
	void updateRole(OperatorCredential credential,RoleVo role) throws TqException;
	/**
	 * 角色是否在使用中
	 * @param roleId
	 * @return
	 */
	boolean isRoleInUse(Integer roleId);
	/**
	 * 删除角色
	 * @param credential
	 * @param roleId
	 * @throws TqException
	 */
	void deleteRole(OperatorCredential credential,Integer roleId) throws TqException;
}
