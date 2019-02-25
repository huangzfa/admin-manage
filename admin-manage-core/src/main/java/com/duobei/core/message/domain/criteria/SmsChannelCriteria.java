package com.duobei.core.message.domain.criteria;

import com.duobei.common.util.Pagination;

public class SmsChannelCriteria extends Pagination{

	/**
	 * 通道编码
	 */
	private String smsChannelCode;

	/**
	 * 通道名称
	 */
	private String smsChannelName;

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

	public String getSmsChannelCode() {
		return smsChannelCode;
	}

	public void setSmsChannelCode(String smsChannelCode) {
		this.smsChannelCode = smsChannelCode;
	}

	public String getSmsChannelName() {
		return smsChannelName;
	}

	public void setSmsChannelName(String smsChannelName) {
		this.smsChannelName = smsChannelName;
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
