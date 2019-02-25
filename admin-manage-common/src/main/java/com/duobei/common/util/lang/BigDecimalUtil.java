package com.duobei.common.util.lang;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

/**
 * 大整数操作工具类
 *
 * @author JingChenglong 2018/11/13 19:47
 *
 */
public class BigDecimalUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int DEF_DIV_SCALE = 10;

	public static double add(double... params) {
		BigDecimal b1 = new BigDecimal(0);
		double[] arr$ = params;
		int len$ = params.length;

		for (int i$ = 0; i$ < len$; ++i$) {
			double param = arr$[i$];
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.add(b2);
		}

		return b1.doubleValue();
	}

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	public static double sub(double... params) {
		int len = params.length;
		if (len < 1) {
			return 0.0D;
		} else {
			BigDecimal b1 = new BigDecimal(Double.toString(params[0]));

			for (int i = 1; i < len; ++i) {
				BigDecimal b2 = new BigDecimal(Double.toString(params[i]));
				b1 = b1.subtract(b2);
			}

			return b1.doubleValue();
		}
	}

	public static double mul(double... params) {
		BigDecimal b1 = new BigDecimal(1);
		double[] arr$ = params;
		int len$ = params.length;

		for (int i$ = 0; i$ < len$; ++i$) {
			double param = arr$[i$];
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.multiply(b2);
		}

		return b1.doubleValue();
	}

	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, scale, 4).doubleValue();
		}
	}

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal b = new BigDecimal(Double.toString(v));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, 4).doubleValue();
		}
	}

	public static double round(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, 4).doubleValue();
	}

	public static double round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, 4).doubleValue();
		}
	}

	public static double round(String v) {
		if (StringUtil.isBlank(v)) {
			return 0.0D;
		} else {
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, 2, 4).doubleValue();
		}
	}

	public static double decimal(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal b = new BigDecimal(Double.toString(v));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, 1).doubleValue();
		}
	}

	public static BigDecimal getBigDecimal(String str) {
		if (!StringUtil.isNumber(str)) {
			str = "0";
		}

		BigDecimal decimal = new BigDecimal(str);
		return decimal.setScale(2, 6);
	}

/*	*//**
	 * BigDecimal 加法运算 做null 处理 至少需要两个参数以上
	 * @param params
	 * @return
	 *//*
	public static BigDecimal addByNull(BigDecimal... params){
		BigDecimal[] arr = params;
		int len = params.length;
		BigDecimal result = BigDecimal.ZERO;
		if(len < 2){

		}
		while(len > 0 ){
			if(arr[len-1] != null){
				result = result.add(arr[len-1]);
			}
			len = len - 1;
		}
		return result;
	}*/

	/**
	 *
	 * @param basic 基数
	 * @param params 累加多参
	 * @return
	 */
	public static BigDecimal addByNull(BigDecimal basic,BigDecimal... params){


		return addByNullAndScale(2,BigDecimal.ROUND_HALF_UP,basic,params);
	}

	/**
	 * @param scale 小数点保留位数
	 * @param round 进位机制 四舍五入...
	 * @param basic 基数
	 * @param params 累加多参
	 * @return
	 */
	public static BigDecimal addByNullAndScale(int scale,int round,BigDecimal basic,BigDecimal... params){
		int len = params.length;
		if(basic == null){
			basic = BigDecimal.ZERO;
		}
		while(len > 0 ){
			if(params[len-1] != null){
				basic = basic.add(params[len-1]);
			}
			len = len - 1;
		}
		return basic.setScale(scale,round);
	}

	/**
	 *
	 * @param basic 基数
	 * @param derate1 被减数 保证至少有一个以上的减数
	 * @param params 多参
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal subtractByNull(BigDecimal basic,BigDecimal derate1,BigDecimal... params) {

		return subtractByNullAndScale(2,BigDecimal.ROUND_HALF_UP,basic,derate1,params);
	}


	/**
	 * @param scale 小数点保留位数
	 * @param round 进位机制 四舍五入...
	 * @param basic 基数
	 * @param derate1 被减数
	 * @param params 多参
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal subtractByNullAndScale(int scale,int round,BigDecimal basic,BigDecimal derate1,BigDecimal... params) {
		int len = params.length;
		if(derate1 == null ){
			derate1 = BigDecimal.ZERO;
		}
		if(basic == null){
			basic = BigDecimal.ZERO;
		}

		while(len > 0 ){
			if(params[len-1] != null){
				derate1 = derate1.add(params[len-1]);
			}
			len = len - 1;
		}
		return basic.subtract(derate1).setScale(scale,round);
	}

	public static void main(String[] args) throws Exception{
		BigDecimal one = new BigDecimal(1.2);
		BigDecimal one1 = new BigDecimal(1.2);
		BigDecimal two = new BigDecimal(2.4);
		BigDecimal three = new BigDecimal(3.6);
		BigDecimal four = new BigDecimal(4.8);

		Random random = new Random();


		System.out.println(random.nextInt(100));


	}

}