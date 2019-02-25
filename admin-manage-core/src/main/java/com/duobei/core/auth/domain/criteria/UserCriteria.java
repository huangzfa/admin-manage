package com.duobei.core.auth.domain.criteria;

import com.duobei.common.util.Pagination;


public class UserCriteria extends Pagination {
	
	private static final long serialVersionUID = -6857087480897989821L;
	
	private Integer organId;
	private String pathLike;
	private Integer selectOrganId;//选择的组织
	private String loginName;
	private String realName;
	private Integer opId;//操作员id
	private String menuTypeZd;//菜单类型
	private String menuStateZd;//菜单状态
	
	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public String getMenuStateZd() {
		return menuStateZd;
	}
	public void setMenuStateZd(String menuStateZd) {
		this.menuStateZd = menuStateZd;
	}
	public String getMenuTypeZd() {
		return menuTypeZd;
	}
	public void setMenuTypeZd(String menuTypeZd) {
		this.menuTypeZd = menuTypeZd;
	}
	public Integer getOrganId() {
		return organId;
	}
	public void setOrganId(Integer organId) {
		this.organId = organId;
	}
	public String getPathLike() {
		return pathLike;
	}
	public void setPathLike(String pathLike) {
		this.pathLike = pathLike;
	}
	public Integer getSelectOrganId() {
		return selectOrganId;
	}
	public void setSelectOrganId(Integer selectOrganId) {
		this.selectOrganId = selectOrganId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Override
	public String getDefaultSort() {
		return null;
	}
	@Override
	public String[] getValidSortFields() {
		return null;
	}
	
	
}
