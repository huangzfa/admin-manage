package com.duobei.common.config;

/**
 * 系统配置项
 * @author Hong
 *
 */
public class GlobalItem {
	
	private String key;//键
	private String val;//值
	private String title;//标题
	private String desc;//说明
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "GlobalItem [key=" + key + ", val=" + val + ", title=" + title
				+ ", desc=" + desc + "]";
	}
	
}
