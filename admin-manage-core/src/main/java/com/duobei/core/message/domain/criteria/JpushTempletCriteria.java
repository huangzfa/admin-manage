package com.duobei.core.message.domain.criteria;

import com.duobei.common.util.Pagination;

public class JpushTempletCriteria extends Pagination{

	/**
	 * 模板类型编码
	 */
	private String templetCode;

	/**
	 * 模板标题
	 */
	private String templetTitle;

	public String getTempletCode() {
		return templetCode;
	}

	public void setTempletCode(String templetCode) {
		this.templetCode = templetCode;
	}

	public String getTempletTitle() {
		return templetTitle;
	}

	public void setTempletTitle(String templetTitle) {
		this.templetTitle = templetTitle;
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
