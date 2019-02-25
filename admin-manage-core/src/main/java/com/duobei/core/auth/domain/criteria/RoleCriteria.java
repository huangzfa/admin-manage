package com.duobei.core.auth.domain.criteria;

import com.duobei.common.util.Pagination;


public class RoleCriteria extends Pagination {
	private static final long serialVersionUID = -5284846518881653872L;
	
	private String roleName;
	private String roleStateZd;
	private Integer opId;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStateZd() {
		return roleStateZd;
	}

	public void setRoleStateZd(String roleStateZd) {
		this.roleStateZd = roleStateZd;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	@Override
	public String getDefaultSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getValidSortFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
