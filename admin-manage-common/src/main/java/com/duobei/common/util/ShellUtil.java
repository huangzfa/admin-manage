package com.duobei.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;



public class ShellUtil {

	public static BigDecimal toBigDecimal(String number){
		return toBigDecimal(number, "0");
	}
	
	public static BigDecimal toBigDecimal(String number,String defaultVal){
		if (StringUtils.isBlank(number)) {
			return new BigDecimal(0);
		}
		try {
			return new BigDecimal(number.trim());
		} catch (Exception e) {
			if (StringUtils.isBlank(defaultVal)) {
				return new BigDecimal(0);
			}
			try {
				return new BigDecimal(defaultVal.trim());
			} catch (Exception e2) {
			}
			return new BigDecimal(0);
		}
	}
}
