package com.duobei.core.manage.auth.domain.vo;

import com.duobei.core.manage.auth.domain.OrganRule;

public class OrganRuleVo extends OrganRule {
	private static final long serialVersionUID = -6544884365219851534L;
	private String supOrganTypeCode;//上级组织类型编码
	private String supOrganTypeName;//上级组织类型名称
	private String subOrganTypeCode;//下级组织类型编码
	private String subOrganTypeName;//下级组织类型名称
	
	
	public String getSupOrganTypeCode() {
		return supOrganTypeCode;
	}
	public void setSupOrganTypeCode(String supOrganTypeCode) {
		this.supOrganTypeCode = supOrganTypeCode;
	}
	public String getSubOrganTypeCode() {
		return subOrganTypeCode;
	}
	public void setSubOrganTypeCode(String subOrganTypeCode) {
		this.subOrganTypeCode = subOrganTypeCode;
	}
	public String getSupOrganTypeName() {
		return supOrganTypeName;
	}
	public void setSupOrganTypeName(String supOrganTypeName) {
		this.supOrganTypeName = supOrganTypeName;
	}
	public String getSubOrganTypeName() {
		return subOrganTypeName;
	}
	public void setSubOrganTypeName(String subOrganTypeName) {
		this.subOrganTypeName = subOrganTypeName;
	}
	
}
