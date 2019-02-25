package com.duobei.common.util;

import java.text.DecimalFormat;

/**
 * 格式化十进制数字工具类
 * @author 洪捃能
 *
 */
public class DecimalFormatUtil {
	public static DecimalFormat format_00=new DecimalFormat("0.00");
	public static DecimalFormat format_000=new DecimalFormat("000");
	public static String format_00(Object o){
		if (o == null) {
			return "0.00";
		}
		return format_00.format(o);
	}
	public static String format_000(Object o){
		if (o == null) {
			return "000";
		}
		return format_000.format(o);
	}
	
	public static void main(String[] args) {
		System.out.println(format_000("我"));
	}
}
