package com.duobei.core.auth.service;

import java.util.List;
import java.util.Set;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.Operator;
import com.duobei.core.auth.domain.OperatorRoleKey;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.UserCriteria;
import com.duobei.core.auth.domain.vo.OperatorVo;
import com.duobei.exception.TqException;

public interface OperatorService {

	/**
	 * 查询登录账号
	 * @param loginName 登录名
	 * @return
	 */
	Operator queryOperatorByLoginName(String loginName);
	/**
	 * 查询用户
	 * @param opId
	 * @return
	 * @throws TqException
	 */
	Operator queryOperatorById(Integer opId)throws TqException;
	OperatorVo queryOperatorVoById(Integer opId) throws TqException;
	/**
	 * 根据用户名查询菜单
	 * @param userName
	 * @return
	 */
	List<Menu> queryMenuByOperator(OperatorCredential credential);
	
	/**
	 * 查询操作员，根据组织
	 * @param topOrganId
	 * @param userCriteria
	 * @return
	 * @throws TqException
	 */
	ListVo<OperatorVo> queryOperatorList(UserCriteria userCriteria)throws TqException;
	
	/**
	 * 添加用户
	 * @param credential
	 * @param operator
	 * @throws TqException
	 * @throws TqException 
	 */
	void addOperator(OperatorCredential credential, Operator operator)throws TqException, TqException;
	/**
	 * 更新用户
	 * @param credential
	 * @param operator
	 * @throws TqException
	 */
	void updateOperator(OperatorCredential credential, Operator operator)throws TqException;
	/**
	 * 删除用户
	 * @param credential
	 * @param opId
	 * @throws TqException
	 */
	void deleteOperator(OperatorCredential credential, Integer opId)throws TqException;
	/**
	 * 查询用户拥有的角色
	 * @param opId
	 * @return
	 * @throws TqException
	 */
	List<OperatorRoleKey> queryOperatorRoleIds(Integer opId)throws TqException;
	/**
	 * 分配角色
	 * @param opId
	 * @param selectRoleIds
	 * @throws TqException
	 */
	void assignRoles(Integer opId, String selectRoleIds)throws TqException;
	/**
	 * 查询用户权限操作
	 * @param opId
	 * @return
	 * @throws TqException
	 */
	Set<String> queryOperatorPermission(Integer opId)throws TqException;
	/**
	 * 修改密码
	 * @param opId
	 * @param oldPassword
	 * @param newPassword
	 * @throws TqException
	 */
	void modifyLoginPwd(Integer opId, String oldPassword, String newPassword)throws TqException;
	
	/**
	 * 登录成功后更新信息
	 * @param credential
	 */
	void updateOperatorForLogin(OperatorCredential credential) throws TqException;
	/**
	 * 查询sessionId
	 * @param opId
	 * @return
	 */
	String querySessionId(Integer opId);

}
