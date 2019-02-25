package com.duobei.common.util.lang;

import java.util.Collection;

/**
 * 集合类工具
 * 
 * @author JingChenglong 2018/11/05 10:57
 *
 */
public class CollectionUtil {

	/**
	 * 判断是否为空
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {

		return (collection == null || collection.size() == 0);
	}

	/**
	 * 判断是否非空
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {

		return (collection != null && collection.size() > 0);
	}
}