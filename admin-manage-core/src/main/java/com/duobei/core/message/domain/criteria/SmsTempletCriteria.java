package com.duobei.core.message.domain.criteria;

import com.duobei.common.util.Pagination;

public class SmsTempletCriteria extends Pagination{
	/**
	 * 模板类型编码
	 */
	private String templetCode;

	/**
	 * 短信类型编码
	 */
	private String smsUserfulCode;

	public String getSmsUserfulCode() {
		return smsUserfulCode;
	}

	public void setSmsUserfulCode(String smsUserfulCode) {
		this.smsUserfulCode = smsUserfulCode;
	}

	public String getTempletCode() {
		return templetCode;
	}

	public void setTempletCode(String templetCode) {
		this.templetCode = templetCode;
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
