package com.duobei.common.util;


/**
 * 系统判断
 * 
 * @author sandy
 */
public class OsUtil {

	public static boolean isWindows() {
		boolean iswindows = false;
		if (System.getProperties().getProperty("os.name").toUpperCase()
				.indexOf("WINDOWS") != -1) {
			iswindows = true;
		}
		return iswindows;
	}
	
	
	public static boolean isLinux() {
		boolean iswindows = false;
		if (System.getProperties().getProperty("os.name").toLowerCase()
				.indexOf("linux") != -1) {
			iswindows = true;
		}
		return iswindows;
	}

	public static boolean isMac() {
		boolean ismac = false;
		if (System.getProperties().getProperty("os.name").toLowerCase()
				.indexOf("mac") != -1) {
			ismac = true;
		}
		return ismac;
	}
}
