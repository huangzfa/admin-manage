package com.duobei.common.config;

import com.duobei.common.util.PropertiesUtil;

public class GlobalProperties {
	private static GlobalProperties singleton = null;
	private static PropertiesUtil properties;

	public static GlobalProperties getInstance() {
		if (singleton == null) {
			synchronized (GlobalProperties.class) {
				if (singleton == null) {
					singleton = new GlobalProperties();
					properties = PropertiesUtil.getInstance("global");
				}
			}
		}
		return singleton;
	}

	public static String getStringVal(String key){
		getInstance();
		return properties.getPropertyAsString(key);
	}
}
