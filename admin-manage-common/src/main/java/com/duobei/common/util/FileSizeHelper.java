package com.duobei.common.util;

import java.text.DecimalFormat;

public class FileSizeHelper {
	public static long ONE_KB = 1024L;
	public static long ONE_MB = ONE_KB * 1024L;
	public static long ONE_GB = ONE_MB * 1024L;
	public static long ONE_TB = ONE_GB * 1024L;
	public static long ONE_PB = ONE_TB * 1024L;

	public static String getHumanReadableFileSize(Long fileSize) {
		if (fileSize == null)
			return null;
		return getHumanReadableFileSize(fileSize.longValue());
	}

	public static String getHumanReadableFileSize(long fileSize) {
		if (fileSize < 0L) {
			return String.valueOf(fileSize);
		}
		String result = getHumanReadableFileSize(fileSize, ONE_PB, "PB");
		if (result != null) {
			return result;
		}

		result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
		if (result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
		if (result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
		if (result != null) {
			return result;
		}
		result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
		if (result != null) {
			return result;
		}
		return String.valueOf(fileSize) + "B";
	}

	private static String getHumanReadableFileSize(long fileSize, long unit,
			String unitName) {
		if (fileSize == 0L)
			return "0";

		if (fileSize / unit >= 1L) {
			double value = fileSize / unit;
			DecimalFormat df = new DecimalFormat("######.##" + unitName);
			return df.format(value);
		}
		return null;
	}
	
}
