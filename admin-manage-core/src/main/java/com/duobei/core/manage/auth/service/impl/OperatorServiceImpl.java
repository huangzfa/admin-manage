package com.duobei.core.manage.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.duobei.common.util.Constants;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.core.manage.auth.domain.vo.OperatorRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.common.config.Global;
import com.duobei.common.util.encrypt.PasswordUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.dao.MenuDao;
import com.duobei.core.manage.auth.dao.OperatorDao;
import com.duobei.core.manage.auth.dao.OperatorRoleDao;
import com.duobei.core.manage.auth.dao.mapper.OperatorMapper;
import com.duobei.core.manage.auth.dao.mapper.OperatorRoleMapper;
import com.duobei.core.manage.auth.domain.Menu;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.OperatorRoleKey;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.auth.domain.criteria.MenuCriteria;
import com.duobei.core.manage.auth.domain.criteria.RoleCriteria;
import com.duobei.core.manage.auth.domain.criteria.UserCriteria;
import com.duobei.core.manage.auth.domain.vo.OperatorVo;
import com.duobei.core.manage.auth.helper.MenuHelper;
import com.duobei.core.manage.auth.service.OperatorService;
import com.duobei.core.manage.auth.service.OrganService;
import com.duobei.dic.ZD;
import com.duobei.dic.ZDHelper;
import com.duobei.common.exception.TqException;

/**
 * 运维人员服务类
 * 
 * @author Hong
 *
 */
@Service("OperatorService")
public class OperatorServiceImpl implements OperatorService {
	private final static Logger log = LoggerFactory.getLogger(OperatorServiceImpl.class);
	@Autowired
	private OperatorMapper operatorMapper;
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private OperatorRoleMapper operatorRoleMapper;
	@Autowired
	private OperatorRoleDao operatorRoleDao;
	@Autowired
	private OrganService organService;

	@Override
	public Operator queryOperatorByLoginName(String loginName) {
		UserCriteria criteria = new UserCriteria();
		criteria.setLoginName(loginName);
		return operatorDao.queryOperatorByLoginName(criteria);
	}

	@Override
	public Operator queryOperatorById(Integer opId) throws TqException {
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		Operator o = operatorMapper.selectByPrimaryKey(opId);
		if (Global.delete_not == o.getIsDelete()) {
			return o;
		}
		return null;
	}

	@Override
	public OperatorVo queryOperatorVoById(Integer opId) throws TqException {
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		UserCriteria criteria = new UserCriteria();
		criteria.setOpId(opId);
		return operatorDao.queryOperatorVoById(criteria);
	}

	@Override
	public List<Menu> queryMenuByOperator(OperatorCredential credential) {
		List<Menu> menus = null;
		MenuCriteria criteria = new MenuCriteria();
		criteria.setMenuTypeZd(ZD.menuType_m);
		criteria.setMenuId(MenuHelper.rootMenuId);
		criteria.setStateZd(ZD.state_open);
		if (credential.isSuperAdmin()) {
			menus = menuDao.queryAdminMenu(criteria);
		} else {
			criteria.setOpId(credential.getOpId());
			menus = menuDao.queryUserMenu(criteria);
		}
		if (menus != null && menus.size() > 0) {
			List<Menu> sortMenus = MenuHelper.sortMenu(menus, MenuHelper.rootMenuId);
			return sortMenus;
		}
		return null;
	}

	@Override
	public ListVo<OperatorVo> queryOperatorList(UserCriteria userCriteria) throws TqException {
		if (userCriteria.getSelectOrganId() == null) {
			throw new TqException("所属组织id不能为空");
		}
		if (StringUtils.isBlank(userCriteria.getLoginName())) {
			userCriteria.setLoginName(null);
		}
		userCriteria.setPathLike("#" + userCriteria.getSelectOrganId().intValue() + "#");

		int total = operatorDao.countByCriteria(userCriteria);
		List<OperatorVo> list = null;
		if (total > 0) {
			list = operatorDao.queryOperatorList(userCriteria);
			//获取所有人员id
			List<Integer> opIds =list.stream()
					.map(OperatorVo::getOpId)
					.collect(Collectors.toList());

			//查询所有人的角色，并分配
			List<OperatorRoleVo> list2 = operatorRoleDao.getRoleByOpIds(opIds);
			for(OperatorVo operator : list ){
				for(OperatorRoleVo roleVo:list2){
					if( operator.getOpId().equals(roleVo.getOpId())){
						if(StringUtil.isBlank(operator.getRoleName())){
							operator.setRoleName(roleVo.getRoleName());
						}else{
							operator.setRoleName(operator.getRoleName()+","+roleVo.getRoleName());
						}
					}
				}
			}

		}
		return new ListVo<OperatorVo>(total, list);
	}

	@Override
	public void addOperator(OperatorCredential credential, Operator operator) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (operator == null) {
			throw new TqException("员工数据不能为空");
		}
		// 基础校验
		beanValidator(operator);
		// 校验登录账号是否已经存在
		if (queryOperatorByLoginName(operator.getLoginName()) != null) {
			throw new TqException("登录账号已存在");
		}
		// 校验组织
		if (organService.queryOrganById(operator.getOrganId()) == null) {
			throw new TqException("所属组织不存在");
		}
		try {
			operator.setLoginPwd(PasswordUtil.encryptPwdForSalt(Constants.LOGIN_PASSWORD));
		} catch (Exception e) {
			throw new TqException("生成员工失败,初始密码生成失败", e);
		}
		operator.setIsDelete(Global.delete_not);
		operator.setAddOperatorId(credential.getOpId());
		operator.setAddTime(new Date());

		if (1 != operatorMapper.insertSelective(operator)) {
			throw new TqException("员工入库失败");
		}
	}

	@Override
	public void updateOperator(OperatorCredential credential, Operator operator) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (operator == null) {
			throw new TqException("员工数据不能为空");
		}
		if (operator.getOpId() == null) {
			throw new TqException("员工id不能为空");
		}
		// 基础校验
		beanValidator(operator);
		Operator oldOperator = queryOperatorById(operator.getOpId());
		if (oldOperator == null) {
			throw new TqException("员工不存在");
		}
		if (!oldOperator.getLoginName().equals(operator.getLoginName())) {
			throw new TqException("员工登录账号不能修改");
		}
		operator.setLoginName(null);
		operator.setLoginPwd(null);
		operator.setOrganId(null);

		if (1 != operatorMapper.updateByPrimaryKeySelective(operator)) {
			throw new TqException("更新数据库失败");
		}
	}

	// 校验登录账号
	private boolean beanValidator(Operator operator) throws TqException {
		if (operator == null) {
			throw new TqException("数据不能为空");
		}
		if (operator.getOrganId() == null) {
			throw new TqException("所属组织不能为空");
		}
		if (StringUtils.isBlank(operator.getLoginName())) {
			throw new TqException("账号不能为空");
		}
		if (StringUtils.isBlank(operator.getRealName())) {
			throw new TqException("姓名不能为空");
		}
		// 状态
		if (StringUtils.isBlank(operator.getOperatorState())) {
			throw new TqException("状态不能为空");
		}
		// 状态是否合法
		if (!ZDHelper.validateState(operator.getOperatorState())) {
			throw new TqException("无效的状态值");
		}
		return true;
	}

	@Override
	public void deleteOperator(OperatorCredential credential, Integer opId) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		Operator operator = queryOperatorById(opId);
		if (operator == null) {
			throw new TqException("员工不存在");
		}
		Operator updateOperator = new Operator();
		updateOperator.setOpId(operator.getOpId());
		updateOperator.setIsDelete(operator.getOpId());

		if (1 != operatorMapper.updateByPrimaryKeySelective(updateOperator)) {
			throw new TqException("更新数据库失败");
		}
	}

	@Override
	public List<OperatorRoleKey> queryOperatorRoleIds(Integer opId) throws TqException {
		RoleCriteria roleCriteria = new RoleCriteria();
		roleCriteria.setOpId(opId);
		roleCriteria.setRoleStateZd(ZD.state_open);
		return operatorRoleDao.queryOperatorRoleIds(roleCriteria);
	}

	@Override
	public void assignRoles(Integer opId, String selectRoleIds) throws TqException {
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		List<OperatorRoleKey> oldRoleIds = queryOperatorRoleIds(opId);
		if (StringUtils.isBlank(selectRoleIds)) {
			if (oldRoleIds != null && oldRoleIds.size() > 0) {
				for (OperatorRoleKey srKey : oldRoleIds) {
					if (1 != operatorRoleMapper.deleteByPrimaryKey(srKey)) {
						log.warn("更新员工角色-删除opId[" + opId + "]，roleId[" + srKey.getRoleId() + "]更新库失败");
					}
				}
			}
		} else {
			if (oldRoleIds == null) {
				oldRoleIds = new ArrayList<OperatorRoleKey>();
			}
			String[] roleIds = selectRoleIds.split(",");
			// 删除被取消的角色
			for (OperatorRoleKey srKey : oldRoleIds) {
				boolean isFind = false;// 是否存在
				Integer oldRoleId = srKey.getRoleId();
				for (String newRoleId : roleIds) {
					if (StringUtils.isNotBlank(newRoleId)) {
						if (oldRoleId.equals(newRoleId)) {
							isFind = true;
							break;
						}
					}
				}
				if (!isFind) {
					if (1 != operatorRoleMapper.deleteByPrimaryKey(srKey)) {
						log.warn("更新员工角色-删除opId[" + opId + "]，roleId[" + oldRoleId + "]更新库失败");
					}
				}
			}
			// 新增选中的角色
			for (String newRoleId : roleIds) {
				if (StringUtils.isNotBlank(newRoleId)) {
					boolean isFind = false;// 是否存在
					for (OperatorRoleKey srKey : oldRoleIds) {
						if (newRoleId.equals(srKey.getRoleId())) {
							isFind = true;
							break;
						}
					}
					if (!isFind) {
						OperatorRoleKey sr = new OperatorRoleKey();
						sr.setOpId(opId);
						sr.setRoleId(new Integer(newRoleId));
						if (1 != operatorRoleMapper.insertSelective(sr)) {
							log.warn("更新员工角色-新增opId[" + opId + "]，roleId[" + newRoleId + "]入库失败");
						}
					}
				}
			}
		}
	}

	@Override
	public Set<String> queryOperatorPermission(Integer opId) throws TqException {
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.setOpId(opId);
		userCriteria.setMenuTypeZd(ZD.menuType_mo);
		userCriteria.setMenuStateZd(ZD.state_open);
		return operatorDao.queryOperatorPermission(userCriteria);
	}

	@Override
	public void modifyLoginPwd(Integer opId, String oldPassword, String newPassword) throws TqException {
		if (opId == null) {
			throw new TqException("员工id不能为空");
		}
		if (StringUtils.isBlank(oldPassword)) {
			throw new TqException("旧密码不能为空");
		}
		if (StringUtils.isBlank(newPassword)) {
			throw new TqException("新密码不能为空");
		}
		if (oldPassword.trim().equals(newPassword.trim())) {
			throw new TqException("旧密码和新密码不能相同");
		}
		Operator operator = queryOperatorById(opId);
		if (operator == null) {
			throw new TqException("员工不存在");
		}
		try {
			operator.setLoginPwd(PasswordUtil.encryptPwdForSalt(newPassword));
		} catch (Exception e) {
			if (e instanceof TqException) {
				throw (TqException) e;
			}
			log.error("校验密码异常", e);
			throw new TqException("校验密码错误,请查看错误日志");
		}
		Operator updateOperator = new Operator();
		updateOperator.setOpId(opId);
		try {
			updateOperator.setLoginPwd(PasswordUtil.encryptPwdForSalt(newPassword));
		} catch (Exception e) {
			log.error("加密新密码异常", e);
			throw new TqException("加密新密码异常,请查看错误日志");
		}
		if (1 != operatorMapper.updateByPrimaryKeySelective(updateOperator)) {
			throw new TqException("修改密码失败，更新数据库失败");
		}
	}

	@Override
	public void updateOperatorForLogin(OperatorCredential credential) throws TqException{
		if (credential == null) {
			throw new RuntimeException("登录失败");
		}
		Operator updateOperator = new Operator();
		updateOperator.setOpId(credential.getOpId());
		updateOperator.setLoginIp(credential.getIp());
		updateOperator.setLoginTime(credential.getLoginTime());
		updateOperator.setSessionId(String.valueOf(credential.getSessionId()));
		updateOperator.setLastLoginIp(credential.getLastLoginIp());
		updateOperator.setLastLoginTime(credential.getLastLoginTime());
		if (1 != operatorMapper.updateByPrimaryKeySelective(updateOperator)) {
			log.warn("更新员工登录后信息失败-opId[" + credential.getOpId() + "]更新库失败");
			throw new RuntimeException("登录失败");
		}
	}

	@Override
	public String querySessionId(Integer opId) {
		try {
			Operator o = queryOperatorById(opId);
			return o.getSessionId();
		} catch (TqException e) {
		}
		return null;
	}

}
