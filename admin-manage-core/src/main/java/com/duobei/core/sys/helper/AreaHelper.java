package com.duobei.core.sys.helper;

public class AreaHelper {
	/**
	 * 是否是直辖市 北京 天津 上海 重庆
	 * @param shengId
	 * @return
	 */
	public static boolean isZhiXiaShi(long shengId) {
		if(shengId == 110000L || shengId == 120000L || shengId == 310000L || shengId == 500000L) {
			return true;
		}
		return false;
	}
}
