package com.duobei.common.util.lang;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * 将list 拆分List<list<>>
	 * @param source   要拆分的list
	 * @param n        每个list元素个数
	 * @return
	 */
	public static List<List<Object>> averageAssign(List<Object> source, int n){
		List<List<Object>> result=new ArrayList<>();
		if( source.size()<= n){
			result.add(source);
		}else{
			int index_1 = 0,index_2 = 0;
			while( index_2< source.size() ){
				List<Object> value=null;
				index_2 = index_2 + n;
				if( index_2 >= source.size()){
					index_2 = source.size();
				}
				value=source.subList(index_1 , index_2);
				index_1 = index_2;
				result.add(value);
			}
		}
		return result;
	}
}