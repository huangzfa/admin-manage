package com.duobei.core.auth.domain.vo;

import com.duobei.core.auth.domain.Organ;

public class OrganVo extends Organ {
	private static final long serialVersionUID = -5694813451668460251L;
	private String organTypeCode;
	private String organTypeName;
	private Integer selectOrganId;//选择的组织
	
	public String getOrganTypeCode() {
		return organTypeCode;
	}

	public void setOrganTypeCode(String organTypeCode) {
		this.organTypeCode = organTypeCode;
	}

	public String getOrganTypeName() {
		return organTypeName;
	}

	public void setOrganTypeName(String organTypeName) {
		this.organTypeName = organTypeName;
	}

	public Integer getSelectOrganId() {
		return selectOrganId;
	}

	public void setSelectOrganId(Integer selectOrganId) {
		this.selectOrganId = selectOrganId;
	}
	
}
