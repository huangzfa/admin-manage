package com.duobei.core.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.dao.RoleDao;
import com.duobei.core.auth.dao.RoleMenuDao;
import com.duobei.core.auth.dao.mapper.OperatorRoleMapper;
import com.duobei.core.auth.dao.mapper.RoleMapper;
import com.duobei.core.auth.dao.mapper.RoleMenuMapper;
import com.duobei.core.auth.domain.OperatorRoleExample;
import com.duobei.core.auth.domain.Role;
import com.duobei.core.auth.domain.RoleExample;
import com.duobei.core.auth.domain.RoleExample.Criteria;
import com.duobei.core.auth.domain.RoleMenuExample;
import com.duobei.core.auth.domain.RoleMenuKey;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.RoleCriteria;
import com.duobei.core.auth.domain.vo.RoleVo;
import com.duobei.core.auth.service.RoleService;
import com.duobei.dic.ZDHelper;
import com.duobei.common.exception.TqException;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
	private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private OperatorRoleMapper operatorRoleMapper;

	@Override
	public ListVo<RoleVo> queryRoleList(OperatorCredential credential, RoleCriteria roleCriteria) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (roleCriteria == null) {
			throw new TqException("查询条件不能为空");
		}
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(roleCriteria.getRoleStateZd())) {
			criteria.andRoleStateEqualTo(roleCriteria.getRoleStateZd());
		}
		if (StringUtils.isNotBlank(roleCriteria.getRoleName())) {
			criteria.andRoleNameLike("%" + roleCriteria.getRoleName() + "%");
		}
		int total = roleMapper.countByExample(example);
		List<RoleVo> roles = null;
		if (total > 0) {
			roles = roleDao.queryRoleList(roleCriteria);
		}
		return new ListVo<RoleVo>(total, roles);
	}

	@Override
	public RoleVo queryRoleById(Integer roleId) throws TqException {
		if (roleId == null) {
			throw new TqException("角色id不能为空");
		}
		RoleVo role = roleDao.queryRoleById(roleId);
		if (role == null) {
			throw new TqException("角色不存在");
		}
		return role;
	}

	@Override
	public void addRole(OperatorCredential credential, RoleVo role) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (role == null) {
			throw new TqException("角色数据不能为空");
		}
		// 基础校验
		beanValidator(role);
		role.setAddOperatorId(credential.getOpId());
		role.setAddTime(new Date());
		// 入库
		if (1 != roleMapper.insertSelective(role)) {
			throw new TqException("角色入库失败");
		}
		if (role.getRoleId() == null) {
			throw new TqException("角色入库失败");
		}
		Integer roleId = role.getRoleId();
		// 如果选择了权限，则添加
		if (role.getMenuIdList() != null && role.getMenuIdList().size() > 0) {
			for (Integer menuId : role.getMenuIdList()) {
				RoleMenuKey rm = new RoleMenuKey();
				rm.setRoleId(roleId);
				rm.setMenuId(menuId);
				if (1 != roleMenuMapper.insertSelective(rm)) {
					log.warn("添加角色权限roleId[" + roleId + "]，menuId[" + menuId + "]入库失败");
				}
			}
		}
	}

	@Override
	public void updateRole(OperatorCredential credential, RoleVo role) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (role == null) {
			throw new TqException("角色数据不能为空");
		}
		if (role.getRoleId() == null) {
			throw new TqException("角色id不能为空");
		}
		// 基础校验
		beanValidator(role);

		Role oldRole = queryRoleById(role.getRoleId());
		if (oldRole == null) {
			throw new TqException("角色不存在");
		}
		if (1 != roleMapper.updateByPrimaryKeySelective(role)) {
			throw new TqException("更新数据库失败");
		}
		Integer roleId = role.getRoleId();
		// 如果选择了权限，则添加
		if (role.getMenuIdList() == null || role.getMenuIdList().size() == 0) {
			roleMenuDao.deleteByRoleId(roleId);
		} else {
			List<RoleMenuKey> oldRoleMenuList = queryRoleMenuByRoleId(roleId);
			if (oldRoleMenuList == null) {
				oldRoleMenuList = new ArrayList<RoleMenuKey>();
			}
			// 删除被取消的权限
			for (RoleMenuKey rm : oldRoleMenuList) {
				boolean isFind = false;// 是否存在
				Integer oldMenuId = rm.getMenuId();
				for (Integer menuId : role.getMenuIdList()) {
					if (oldMenuId.equals(menuId)) {
						isFind = true;
						break;
					}
				}
				if (!isFind) {
					if (1 != roleMenuMapper.deleteByPrimaryKey(rm)) {
						log.warn("更新角色权限-删除roleId[" + roleId + "]，menuId[" + oldMenuId + "]更新库失败");
					}
				}
			}
			// 新增选中的权限
			for (Integer menuId : role.getMenuIdList()) {
				boolean isFind = false;// 是否存在
				for (RoleMenuKey rm : oldRoleMenuList) {
					if (menuId.equals(rm.getMenuId())) {
						isFind = true;
						break;
					}
				}
				if (!isFind) {
					RoleMenuKey rm = new RoleMenuKey();
					rm.setRoleId(roleId);
					rm.setMenuId(menuId);
					if (1 != roleMenuMapper.insertSelective(rm)) {
						log.warn("更新角色权限-新增roleId[" + roleId + "]，menuId[" + menuId + "]入库失败");
					}
				}
			}
		}
	}

	// 验证角色
	private boolean beanValidator(Role role) throws TqException {
		if (role == null) {
			throw new TqException("角色数据不能为空");
		}
		if (StringUtils.isBlank(role.getRoleName())) {
			throw new TqException("角色名称不能为空");
		}
		// 状态
		if (StringUtils.isBlank(role.getRoleState())) {
			throw new TqException("角色状态不能为空");
		}
		// 状态是否合法
		if (!ZDHelper.validateState(role.getRoleState())) {
			throw new TqException("无效的角色状态值");
		}
		return true;
	}

	@Override
	public boolean isRoleInUse(Integer roleId) {
		OperatorRoleExample example = new OperatorRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return operatorRoleMapper.countByExample(example) > 0;
	}

	@Override
	public void deleteRole(OperatorCredential credential, Integer roleId) throws TqException {
		if (credential == null) {
			throw new TqException("操作员不能为空");
		}
		if (roleId == null) {
			throw new TqException("角色id不能为空");
		}
		Role role = queryRoleById(roleId);
		if (role == null) {
			throw new TqException("角色不存在");
		}
		// 校验角色是否在使用
		if (isRoleInUse(roleId)) {
			throw new TqException("删除失败,角色正在使用中，请解除关联用户再删除");
		}
		if (1 == roleMapper.deleteByPrimaryKey(roleId)) {
			roleMenuDao.deleteByRoleId(roleId);
		} else {
			throw new TqException("删除失败,更新数据库失败");
		}
	}

	@Override
	public List<RoleMenuKey> queryRoleMenuByRoleId(Integer roleId) {
		RoleMenuExample example = new RoleMenuExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return roleMenuMapper.selectByExample(example);
	}
}
