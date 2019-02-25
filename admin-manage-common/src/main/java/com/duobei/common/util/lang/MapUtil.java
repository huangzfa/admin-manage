package com.duobei.common.util.lang;

import java.util.Map;

/**
 * Map工具
 * 
 * @author JingChenglong 2018/10/05 10:58
 *
 */
public class MapUtil {

	/**
	 * 判断map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {

		return (map == null || map.size() == 0);
	}

	/**
	 * 判断map是否非空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {

		return (map != null && map.size() > 0);
	}
}