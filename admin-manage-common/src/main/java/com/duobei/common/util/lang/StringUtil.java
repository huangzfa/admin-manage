package com.duobei.common.util.lang;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * 
 * @author JingChenglong 2018/11/05 10:43
 *
 */
public class StringUtil {

	/** 编码 */
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_ISO88591 = "ISO-8859-1";
	public static final String ENCODING_GB2312 = "gb2312";
	public static final String ENCODEING_DEFAULT = ENCODING_UTF8;

	/** 字节长度 */
	public static final long KBYTE = 1024L;// 1K
	public static final long MBYTE = 1048576L;// 1M
	public static final long GBYTE = 1073741824L;// 1G

	public static final double MONEY_MIN = 0.01D;
	public static final double MONET_MAX = 1000000000.00D;

	public static boolean isEmpty(String str) {

		return ((str == null) || (str.trim().length() == 0));
	}

	public static boolean isEmpty(Object str) {

		return (str == null || "".equals(str));
	}

	public static boolean isNotEmpty(String str) {

		return ((str != null) && (str.trim().length() > 0));
	}

	public static boolean haveEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean haveEmpty(Object... objs) {
		for (Object obj : objs) {
			if (isEmpty(obj)) {
				return true;
			}
		}
		return false;
	}

	public static boolean haveNotEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	public static boolean allEmpty(String... strs) {
		for (String str : strs) {
			if (isNotEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	public static boolean haveNull(Object... objs) {
		for (Object obj : objs) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}

	public static String nullToStr(String str) {

		return str == null ? "" : str;
	}

	public static String nullToStrTrim(String str) {

		return str == null ? "" : str.trim();
	}

	public static String nullToStrTrim(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof String) {
				return nullToStrTrim((String) obj);
			} else {
				return nullToStrTrim(obj.toString());
			}
		}
	}

	/**
	 * 判断是否有空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (isEmpty(str)) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmptyOrZero(Object obj) {
		if (isEmpty(obj)) {
			return true;
		}
		try {
			return Double.valueOf(obj.toString()) == 0.0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取字符串的绝对长度，一个中文字符长度为2
	 * 
	 * @param str
	 * @return
	 */
	public static int getWordLength(String str) {

		return nullToStr(str).replaceAll("[^\\x00-\\xff]", "**").length();
	}

	/**
	 * 判断是否为纯半角字符集
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isHalfAngle(String str) {

		str = nullToStrTrim(str);
		return str.length() == getWordLength(str);
	}

	/**
	 * 获取绝对长度
	 * 
	 * @param resContent
	 * @return
	 */
	public static int getRealLength(String str) {

		return getRealLength(str, ENCODEING_DEFAULT);
	}

	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String getUUID() {

		return UUID.randomUUID().toString().replace("-", "");
	}

	public static int nullToIntZero(String str) {

		str = isEmpty(str) ? "0" : str;
		return Integer.valueOf(str.trim(), 10);
	}

	public static Integer toInteger(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return Integer.valueOf(String.valueOf(obj));
		} catch (Exception e) {
			return null;
		}
	}

	public static double nullToDoubleZero(String str) {

		str = isEmpty(str) ? "0.00" : str;
		return Double.valueOf(str.trim());
	}

	public static long nullToLongZero(String str) {

		str = isEmpty(str) ? "0" : str;
		return Long.valueOf(str.trim(), 10);
	}

	public static boolean nullToBoolean(String str) {

		return isEmpty(str) ? false : Boolean.valueOf(str.trim());
	}

	/**
	 * 转义HTML
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeHTML(String str) {
		if (isEmpty(str)) {
			return "";
		}
		char[] content = new char[str.length()];
		str.getChars(0, str.length(), content, 0);
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&#039;");
				break;
			default:
				sb.append(content[i]);
				break;
			}
		}

		return sb.toString();
	}

	/**
	 * 去除字符串中的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeBlank(String str) {
		if (isEmpty(str)) {
			return str;
		}
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll(" ", "");
		return nullToStrTrim(str);
	}

	/**
	 * 根据参数给定范围获取整数随机数
	 * 
	 * @param accuracy
	 * @return
	 */
	public static int getRandom(int accuracy) {

		return (int) (Math.random() * accuracy);
	}

	/**
	 * 使用给定字符串中的字符生成指定长度的随机字符串
	 * 
	 * @param randString
	 * @param length
	 * @return
	 */
	public static String getRandom(String randString, int length) {
		if (isEmpty(randString) || length <= 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(randString.charAt(rand.nextInt(randString.length())));
		}
		return sb.toString();
	}

	/**
	 * 获取绝对长度
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	public static int getRealLength(String str, String charset) {

		str = nullToStrTrim(str);

		if (isEmpty(str)) {
			return 0;
		}

		try {
			return str.getBytes(charset).length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}

	/**
	 * 将字节数组转换为16进制字符串
	 * 
	 * @param bytes
	 *            待转换的字节数组
	 * @return 转换后的字符串
	 */
	public static String bytesToHexString(byte[] bytes) {

		StringBuilder sb = new StringBuilder(bytes.length);

		String temp = null;

		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(0Xff & bytes[i]);
			if (temp.length() < 2) {
				sb.append(0);
			}
			sb.append(temp);
		}
		return sb.toString();
	}

	/**
	 * 将16进制字符串转换成字节数组
	 * 
	 * @param hex
	 *            16进制字符串
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hex) {
		int len = (hex.length() / 2);
		hex = hex.toUpperCase();
		byte[] result = new byte[len];
		char[] chars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(chars[pos]) << 4 | toByte(chars[pos + 1]));
		}
		return result;
	}

	/**
	 * 将char转换为byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte toByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String replace(String str, String searchStr, String replaceStr) {
		return replace(str, searchStr, replaceStr, -1);
	}

	public static String replace(String str, String searchStr, String replaceStr, int max) {
		if (haveEmpty(str, searchStr, replaceStr) || max == 0) {
			return str;
		}
		int start = 0;
		int end = str.indexOf(searchStr, start);
		if (-1 == end) {
			return str;
		}
		final int replLength = searchStr.length();
		int increase = replaceStr.length() - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= max < 0 ? 16 : max > 64 ? 64 : max;
		final StringBuilder buf = new StringBuilder(str.length() + increase);
		while (end != -1) {
			buf.append(str.substring(start, end)).append(replaceStr);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = str.indexOf(searchStr, start);
		}
		buf.append(str.substring(start));
		return buf.toString();
	}

	public static boolean checkString(String str, String regex) {
		return str.matches(regex);
	}

	public static boolean isNumber(String str) {
		if (isBlank(str)) {
			return false;
		} else {
			Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
			Matcher matcher = regex.matcher(str);
			boolean isMatched = matcher.matches();
			return isMatched;
		}
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static boolean isPhone(String phone) {

		return phone.startsWith("0");
	}

	public static boolean isMobile(String mobile) {

		return mobile.startsWith("1");
	}

	public static boolean checkPhone(String phone) {

		if (isEmpty(phone) || !isHalfAngle(phone) || isBlank(phone)) {
			return false;
		}

		if (phone.length() != 11 && phone.length() != 12) {
			return false;
		}

		String regex = "0([1-9])[0-9]+";

		return phone.matches(regex);
	}

	public static boolean checkMd5(String md5) {

		if (md5.length() != 32) {
			return false;
		}

		return checkString(md5, "[0-9A-Fa-f]+");
	}

	public static boolean checkEmail(String email) {

		email = nullToStrTrim(email);
		if (isEmpty(email)) {
			return false;
		}
		if (!isHalfAngle(email) || email.length() < 5 || email.length() > 60) {
			return false;
		}

		String regex = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

		return email.matches(regex);
	}

	private static boolean checkSubphone(String subphone) {

		if (isEmpty(subphone) || !isHalfAngle(subphone) || isBlank(subphone) || subphone.length() > 5) {
			return false;
		}

		String regex = "[0-9]+";

		return subphone.matches(regex);
	}

	public static boolean checkPhoneNew(String phone) {

		phone = nullToStrTrim(phone);
		if (!isHalfAngle(phone) || isBlank(phone)) {
			return false;
		}

		if (phone.length() > 20) {
			return false;
		}

		if (phone.indexOf("-") < 0) {
			return checkPhone(phone);
		}

		return checkPhone(phone.substring(0, phone.indexOf("-")))
				&& checkSubphone(phone.substring(phone.indexOf("-") + 1));
	}

	public static boolean checkPhone86(String phone) {

		phone = nullToStrTrim(phone);
		if (!isHalfAngle(phone)) {
			return false;
		}

		if (phone.length() != 13 && phone.length() != 14) {
			return false;
		}

		String regex = "860([1-9])[0-9]+";

		return phone.matches(regex);
	}

	public static boolean checkMobile(String mobile) {

		mobile = nullToStrTrim(mobile);
		if (!isHalfAngle(mobile)) {
			return false;
		}

		if (mobile.length() != 11) {
			return false;
		}

		String regex = "^(1([3,4,5,8][0-9]))\\d{8}$";

		return mobile.matches(regex);
	}

	public static boolean checkMobileNew(String mobile) {

		mobile = nullToStrTrim(mobile);
		if (!isHalfAngle(mobile) || isBlank(mobile)) {
			return false;
		}

		if (mobile.length() > 20) {
			return false;
		}

		if (mobile.indexOf("-") < 0) {
			return checkMobile(mobile);
		}

		return checkMobile(mobile.substring(0, mobile.indexOf("-")))
				&& checkSubphone(mobile.substring(mobile.indexOf("-") + 1));
	}

	public static boolean checkMobile86(String mobile) {

		mobile = nullToStrTrim(mobile);
		if (!isHalfAngle(mobile)) {
			return false;
		}

		if (mobile.length() != 13) {
			return false;
		}

		String regex = "^(861([3,4,5,8][0-9]))\\d{8}$";

		return mobile.matches(regex);
	}

	public static boolean check00(String phone) {

		phone = nullToStrTrim(phone);
		if (!isHalfAngle(phone)) {
			return false;
		}

		if (phone.length() != 10) {
			return false;
		}

		String regex = "[400,800][0-9]+";

		return phone.matches(regex);
	}

	public static boolean checkIdcardno(String idcardno) {

		idcardno = nullToStrTrim(idcardno);
		if (!isHalfAngle(idcardno)) {
			return false;
		}

		if (idcardno.length() != 15 && idcardno.length() != 18) {
			return false;
		}

		return checkString(idcardno, "[1-9][0-9][0-9xX]+");
	}

	public static boolean checkPostcode(String postcode) {

		postcode = nullToStrTrim(postcode);
		if (!isHalfAngle(postcode)) {
			return false;
		}

		if (postcode.length() != 6) {
			return false;
		}

		return checkString(postcode, "[1-9][0-9]+");
	}

	public static boolean checkMac(String mac) {

		mac = mac.trim();
		if (isEmpty(mac) || isBlank(mac) || !isHalfAngle(mac)) {
			return false;
		}
		if (mac.length() != 12 && mac.length() != 17) {
			return false;
		}

		return checkString(mac, "([0-9A-Fa-f]{2}){6}") || checkString(mac, "([0-9A-Fa-f]{2}-){5}[0-9A-Fa-f]{2}")
				|| checkString(mac, "([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}");
	}
	// 使用STRING BUFFER 来组装字符串，效率更高
	public static String appendStrs(Object... strings) {
		StringBuffer sb = new StringBuffer();
		for (Object str : strings) {
			sb.append(null2Str(str));
		}
		return sb.toString();
	}
	public static String null2Str(Object str) {
		return (str != null) ? str.toString() : "";
	}
	     /** 把字符串数组按分隔符转化成字符串
     *@param strArr 字符串数组
     *@param separator 分隔符
     *@return
			 */
	public static String turnArrayToStr(String separator,String ...strArr){
		String result = "";
		if(strArr == null || strArr.length < 1){
			return result;
		}
		if(separator == null){
			separator = ",";
		}

		for(String item:strArr){
			result = result + separator + item;
		}
		return result.substring(separator.length());
	}
	public static List<String> splitToList(String source, String sep){
		List<String> result = new ArrayList<String>();
		if (isBlank(source)) {
			return result;
		}
		String[] tempResult = source.split(sep);
		for(String item:tempResult){
			if(StringUtil.isNotEmpty(item)){
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * 去除字符串中所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	 * @param s
	 * @return
	 */
	public static String removeAllBlank(String s){
		String result = "";
		if(null!=s && !"".equals(s)){
			result = s.replaceAll("[　*| *| *|//s*]*", "");
		}
		return result;
	}

	/**
	 * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	 * @param s
	 * @return
	 */
	public static String trim(String s){
		String result = "";
		if(null!=s && !"".equals(s)){
			result = s.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
		}
		return result;
	}

}