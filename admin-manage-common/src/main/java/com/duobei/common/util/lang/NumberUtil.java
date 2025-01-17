package com.duobei.common.util.lang;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数值操作工具
 *
 * @author JingChenglong 2018/11/13 19:45
 *
 */
public class NumberUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static double format(double d, String format) {
		DecimalFormat df = new DecimalFormat(format);
		String ds = df.format(d);
		return Double.parseDouble(ds);
	}

	public static double format2(double d) {
		return BigDecimalUtil.decimal(d, 2);
	}

	public static String format2Str(double d) {
		DecimalFormat df = new DecimalFormat("#####0.00");
		return df.format(BigDecimalUtil.decimal(d, 2));
	}

	public static String format3Str(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(BigDecimalUtil.decimal(d, 2));
	}

	public static String format5Str(double d) {
		DecimalFormat df = new DecimalFormat("0.00000");
		return df.format(BigDecimalUtil.decimal(d, 5));
	}

	public static double format4(double d) {
		return BigDecimalUtil.decimal(d, 4);
	}

	public static double format6(double d) {
		return BigDecimalUtil.decimal(d, 6);
	}

	public static int compare(double x, double y) {
		BigDecimal val1 = new BigDecimal(x);
		BigDecimal val2 = new BigDecimal(y);
		return val1.compareTo(val2);
	}

	public static double ceil(double d, int len) {
		String str = Double.toString(d);
		int a = str.indexOf(".");
		if (a + 3 > str.length()) {
			a = str.length();
		} else {
			a += 3;
		}

		str = str.substring(0, a);
		return Double.parseDouble(str);
	}

	public static double ceil(double d) {
		return ceil(d, 2);
	}

	public static String format(double d) {
		if (d < 1.0E7D) {
			return d + "";
		} else {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			return nf.format(d);
		}
	}

	public static long getLong(String str) {
		if (StringUtil.isBlank(str)) {
			return 0L;
		} else {
			long ret = 0L;

			try {
				ret = Long.parseLong(str);
			} catch (NumberFormatException arg3) {
				ret = 0L;
			}

			return ret;
		}
	}

	public static int getInt(String str) {
		if (StringUtil.isBlank(str)) {
			return 0;
		} else {

			int ret1;
			try {
				ret1 = Integer.parseInt(str);
			} catch (NumberFormatException arg2) {
				ret1 = 0;
			}

			return ret1;
		}
	}

	public static double getDouble(String str) {
		if (StringUtil.isBlank(str)) {
			return 0.0D;
		} else {
			double ret = 0.0D;

			try {
				ret = Double.parseDouble(str);
			} catch (Exception arg3) {
				ret = 0.0D;
			}

			return ret;
		}
	}
	public static double strToDoubleWithDefault(String str, double def) {
		if (StringUtils.isBlank(str)) {
			return def;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return def;
		}
	}
}
