package com.duobei.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {
	/**
	public BigDecimal add(BigDecimal value);//加法
	public BigDecimal subtract(BigDecimal value); //减法 
	public BigDecimal multiply(BigDecimal value); //乘法
	public BigDecimal divide(BigDecimal value); //除法
	*/
	public static void main(String[] args) {
		BigDecimal a=new BigDecimal("12345678199.73234567");
		BigDecimal b=new BigDecimal("22.5");
		BigDecimal c=new BigDecimal("22.5");
		System.out.println(a.divide(b,4,RoundingMode.HALF_UP));
		System.out.println(a.compareTo(b));//大于1
		System.out.println(b.compareTo(a));//小于-1
		System.out.println(b.compareTo(c));//等于0
		System.out.println(a.toString());
		System.out.println(a.negate());
		System.out.println(BigDecimal.ZERO);
	}
}
