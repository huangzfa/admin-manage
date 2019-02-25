package com.duobei.common.config;

import java.util.HashMap;

/**
 * 系统配置
 * @author Hong
 *
 */
public class Global {
	private Global(){}
	
	private static HashMap<String,GlobalItem> items=new HashMap<String,GlobalItem>();
	
	/**
	 * 获取配置项集合
	 * @return
	 */
	public static HashMap<String,GlobalItem> getItems(){
		GlobalBuilder.builder();
		return items;
	}
	/**
	 * 获取配置项
	 * @return
	 */
	public static GlobalItem getItem(String key){
		if (key==null||"".equals(key.trim())) {
			return null;
		}
		return getItems().get(key);
	}
	/**
	 * 获取配置值
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static String getValAsString(String key,String defaultVal){
		GlobalItem item=getItem(key);
		if (item!=null) {
			if(item.getVal()==null||"".equals(item.getVal().trim())){
				return defaultVal;
			}
			return item.getVal().trim();
		}
		return defaultVal;
	}
	/**
	 * 获取配置值
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static String getValAsString(String key){
		return getValAsString(key, null);
	}
	/**
	 * 获取配置值
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static int getValAsInt(String key,int defaultVal){
		GlobalItem item=getItem(key);
		if (item!=null) {
			if(item.getVal()==null||"".equals(item.getVal().trim())){
				return defaultVal;
			}
			try {
				return Integer.parseInt(item.getVal().trim());
			} catch (Exception e) {
				return defaultVal;
			}
		}
		return defaultVal;
	}
	/**
	 * 获取配置值
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static int getValAsInt(String key){
		return getValAsInt(key, 0);
	}
	/**
	 * 未删除
	 */
	public static final int delete_not=0;
	
}
