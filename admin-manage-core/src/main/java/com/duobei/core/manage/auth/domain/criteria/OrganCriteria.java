package com.duobei.core.manage.auth.domain.criteria;

import com.duobei.common.util.Pagination;

public class OrganCriteria extends Pagination{
	private static final long serialVersionUID = -6971157099752486055L;
	
	private Integer selectOrganId;//选择的组织
	private Integer organId;
	private Integer parentOrganId;
	private Integer organRuleId;
	private Integer supOrganTypeId;
	private String pathLike;
	
	public Integer getSelectOrganId() {
		return selectOrganId;
	}

	public void setSelectOrganId(Integer selectOrganId) {
		this.selectOrganId = selectOrganId;
	}

	public Integer getParentOrganId() {
		return parentOrganId;
	}

	public void setParentOrganId(Integer parentOrganId) {
		this.parentOrganId = parentOrganId;
	}

	public String getPathLike() {
		return pathLike;
	}

	public void setPathLike(String pathLike) {
		this.pathLike = pathLike;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getOrganRuleId() {
		return organRuleId;
	}

	public void setOrganRuleId(Integer organRuleId) {
		this.organRuleId = organRuleId;
	}

	public Integer getSupOrganTypeId() {
		return supOrganTypeId;
	}

	public void setSupOrganTypeId(Integer supOrganTypeId) {
		this.supOrganTypeId = supOrganTypeId;
	}

	
	
	
}
