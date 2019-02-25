package com.duobei.core.auth.domain.criteria;

import com.duobei.common.util.Pagination;

public class MenuCriteria extends Pagination{
	private static final long serialVersionUID = -2985507170674224328L;
	private Integer menuId;
	private Integer parentId;
	private String menuTypeZd;//菜单类型
	private String stateZd;
	private Integer opId;//操作员id
	
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuTypeZd() {
		return menuTypeZd;
	}

	public void setMenuTypeZd(String menuTypeZd) {
		this.menuTypeZd = menuTypeZd;
	}

	public String getStateZd() {
		return stateZd;
	}

	public void setStateZd(String stateZd) {
		this.stateZd = stateZd;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
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
