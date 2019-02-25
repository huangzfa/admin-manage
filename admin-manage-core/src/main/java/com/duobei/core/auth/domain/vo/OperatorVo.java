package com.duobei.core.auth.domain.vo;

import com.duobei.core.auth.domain.Operator;

public class OperatorVo extends Operator {
	private static final long serialVersionUID = -799706095604256878L;
	
	private Integer selectOrganId;//选择的组织
	private String organName;
	private String roleName;

	public Integer getSelectOrganId() {
		return selectOrganId;
	}

	public void setSelectOrganId(Integer selectOrganId) {
		this.selectOrganId = selectOrganId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
