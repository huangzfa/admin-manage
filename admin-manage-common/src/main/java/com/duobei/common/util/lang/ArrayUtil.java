package com.duobei.common.util.lang;

/**
 * 数组处理工具类
 * 
 * JingChenglong 2018/11/05 10:57
 *
 */
public class ArrayUtil {

	public static boolean isEmpty(Object[] objArr) {
		return (objArr == null || objArr.length == 0);
	}

	public static boolean isNotEmpty(Object[] objArr) {
		return (objArr != null && objArr.length > 0);
	}
}