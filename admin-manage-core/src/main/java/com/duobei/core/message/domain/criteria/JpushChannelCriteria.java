package com.duobei.core.message.domain.criteria;

import com.duobei.common.util.Pagination;

public class JpushChannelCriteria extends Pagination{

	/**
	 * 通道编码
	 */
	private String jpushChannelCode;

	/**
	 * 通道名称
	 */
	private String jpushChannelName;

	public String getJpushChannelCode() {
		return jpushChannelCode;
	}

	public void setJpushChannelCode(String jpushChannelCode) {
		this.jpushChannelCode = jpushChannelCode;
	}

	public String getJpushChannelName() {
		return jpushChannelName;
	}

	public void setJpushChannelName(String jpushChannelName) {
		this.jpushChannelName = jpushChannelName;
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
