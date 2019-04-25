package com.duobei.common.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class BatchDeliveryResultVo implements Serializable {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4278833792434220655L;
	
	private boolean success;
	private String msg;
	private Integer successCount;
	private Integer failCount;
	private String failFilePath;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public String getFailFilePath() {
		return failFilePath;
	}
	public void setFailFilePath(String failFilePath) {
		this.failFilePath = failFilePath;
	}
	
}
