package com.duobei.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESCoder {
	// 初始向量
	public static final String VIPARA = "aabbccddeeffgghh"; // AES 为16bytes. DES
															// 为8bytes
	// 编码方式
	public static final String charset = "UTF-8";
	// 私钥
	private static final String ASE_KEY = "aabbccddeeffgghh"; // AES固定格式为128/192/256
																// bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptAES128_Base64(String data) {
		// 加密方式： AES128(CBC/PKCS5Padding) + Base64, 私钥：aabbccddeeffgghh
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			// 两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
			SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");
			// 实例化加密类，参数为加密方式，要写全
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密
			// 初始化，此方法可以采用三种方式，按加密算法要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random
			// = new
			// SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			// 加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX,
			// UUE,7bit等等。此处看服务器需要什么编码方式
			byte[] enData = cipher.doFinal(data.getBytes(charset));
			return Base64.encodeBase64String(enData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param encrypted
	 * @return
	 */
	public static String decryptAES128_Base64(String base64Data) {
		try {
			byte[] data = Base64.decodeBase64(base64Data);
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 与加密时不同MODE:Cipher.DECRYPT_MODE
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] deData = cipher.doFinal(data);
			return new String(deData, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decryptAES128_Base64(byte[] base64Data) {
		try {
			byte[] data = Base64.decodeBase64(base64Data);
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 与加密时不同MODE:Cipher.DECRYPT_MODE
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] deData = cipher.doFinal(data);
			return new String(deData, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String content = "https://das.base.shuju.aliyun.com/token3rd/dashboard/view/pc.htm?pageId=4485a694-6dda-44e9-bcca-43ddce6618e9&accessToken=1b9cc47f2c3b598b501c0bb61193d39c";
		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = encryptAES128_Base64(content);

		System.out.println("加密并base64后：" + encryptResult);
		// 解密
		String decryptResult = decryptAES128_Base64(encryptResult);
		System.out.println("解密后：" + new String(decryptResult));

		System.out.println(content.equals(decryptResult));

		System.out.println("======================================================");
		System.out.println(Base64.encodeBase64String(content.getBytes("UTF-8")));
		System.out.println(com.duobei.common.util.encrypt.Base64.encodeBytes(content.getBytes("UTF-8"), false));

	}
}
