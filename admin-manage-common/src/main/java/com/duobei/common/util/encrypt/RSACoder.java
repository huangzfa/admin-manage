package com.duobei.common.util.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * RSA安全编码组件
 * 
 * @version 1.0
 * @since 1.0
 */
public class RSACoder {
	/** */
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/** */
	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/** */
	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/** */
	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}

	/** */
	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decodeBase64(sign));
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/** */
	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		// 安卓端需写为RSA/None/PKCS1Padding，其他为keyFactory.getAlgorithm()
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/** */
	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/** */
	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	/** */
	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	private static String PUBLICKEY_STRING;

	private static String PRIVATEKEY_STRING;

	@SuppressWarnings("unused")
	private static String getPublicKeyString() throws Exception {
		if (PUBLICKEY_STRING == null) {
			PUBLICKEY_STRING = FileUtils
					.readFileToString(new File(RSACoder.class.getResource("/app_public_key.pem").getFile()));
		}
		return PUBLICKEY_STRING;
	}

	@SuppressWarnings("unused")
	private static String getPrivateKeyString() throws Exception {
		if (PRIVATEKEY_STRING == null) {
			PRIVATEKEY_STRING = FileUtils
					.readFileToString(new File(RSACoder.class.getResource("/app_private_key.pem").getFile()));
		}
		return PRIVATEKEY_STRING;
	}

	public static void initKey(String publicKey, String privateKey) throws Exception {
		PUBLICKEY_STRING = publicKey;
		PRIVATEKEY_STRING = privateKey;
	}

	public static void initKey(File publicKey, File privateKey) throws Exception {
		PUBLICKEY_STRING = FileUtils.readFileToString(publicKey);
		PRIVATEKEY_STRING = FileUtils.readFileToString(privateKey);
	}

	public static String getPUBLICKEY_STRING() {
		return PUBLICKEY_STRING;
	}

	public static String getPRIVATEKEY_STRING() {
		return PRIVATEKEY_STRING;
	}

	public static void main(String[] args) throws Exception {
		String data = "hahah12133";
		// String pubKey= FileUtils.readFileToString(new
		// File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_public_key.pem"));
		// byte[] enBytes = encryptByPublicKey(data.getBytes(),pubKey);
		//
		// String priKey= FileUtils.readFileToString(new
		// File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_private_key.pem"));
		// byte[] deBytes= decryptByPrivate(enBytes,priKey);
		//

		String pubKey = null;// FileUtils.readFileToString(new
								// File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_public_key.pem"));
		pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZySNg9a/ejRcAUfXufFa2IYBnkJhfGmNXlep1NcZmVTTkngaDLbzNTxchCWBNE29zzIvdRFeOMAlnpLtKUHCECMl43hnij4Vdzl91KHgIVo7/16sjAIVdplhiGvLRu574fnAKSsq4wSMmT8uIJJCDCJAq1J95LwbI+xip2LBfUwIDAQAB";
		byte[] enBytes = encryptByPublicKey(data.getBytes(), pubKey);
		System.err.println("enString=" + new String(enBytes));
		String priKey = null;// FileUtils.readFileToString(new
								// File("D:\\workspace\\ctz-web-site\\src\\main\\resources\\app_private_key.pem"));
		priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJnJI2D1r96NFwBR9e58VrYhgGeQmF8aY1eV6nU1xmZVNOSeBoMtvM1PFyEJYE0Tb3PMi91EV44wCWeku0pQcIQIyXjeGeKPhV3OX3UoeAhWjv/XqyMAhV2mWGIa8tG7nvh+cApKyrjBIyZPy4gkkIMIkCrUn3kvBsj7GKnYsF9TAgMBAAECgYAnkBNF8m7YNjvgnTVDsUEtgdjiy9QnQ0ajVbIHjVoJIYqF9hF+1dloiBxPJ3Za3dEiXVDuD5LmxXzl6P810xPeu+d19pBMdw15na45mDvRVBhFVKiYK57wqFarvRFkQsVZo780MxR7tCjUWt9DJa1mlWD/U8CkrvEAIQKGt2LoAQJBAO/AMldnkoi2ShVk3rv7k9HO5X0ej4GlzMKPfpds1oTPw/124Abgf9jI/1F9MeWXxAdcUjCIACF5UbobzG5aVlMCQQCkNWbeD37dDd4LlN3fQTbflhzzgzXWlr1reltJvyyHYYK25MJAYcSmcjIczQ+oN8ka9fIzALDA8IocsDiM3rMBAkEAi6opKWW6pU2cBq6GWngJ+rVOVv8QlnQhvk2db1UF+sSV5ff0Nc1ebMlagvwtrxQkLC2J65+ug9GK3KIJC1gw5QJAMEHxZTkwk05L/yNUwROtSLAY+geJfvL13+wpf9bLA05I1+8Kl1kkLbZJu+UXwNwEIgQ9lXeSemE2dY/kTqH0AQJBAOVX4uZ+aYqzfx8r2E7mRKNxBuMIGZHlGS6yN6tJoe0DTy4xP7SZg42jT51BZDsspypj5v+ybjps1VC8P8qNsG0=";
		byte[] deBytes = decryptByPrivateKey(enBytes, priKey);
		System.err.println("deString=" + new String(deBytes));

		// String enString =
		// encryptDataByRSA64("sendType=1&codeType=2&sendMobile=18019907186&sendEmail=234234@dafd.com");
		// String enString = encryptDataByRSA64("user_name=yzy&pwd=12345");
		// System.out.println(enString);

		// 生成公钥，私钥
		// String publicKey;
		// String privateKey;
		// Map<String, Object> keyMap = RSACoder.genKeyPair();
		// publicKey = RSACoder.getPublicKey(keyMap);
		// privateKey = RSACoder.getPrivateKey(keyMap);
		// System.err.println("公钥: \n\r" + publicKey);
		// System.err.println("私钥： \n\r" + privateKey);
		// 生成公钥，私钥
	}
}
