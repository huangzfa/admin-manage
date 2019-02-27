package com.duobei.utils;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * rsa签名辅助工具类
 *
 * @author ginko
 */
public class RSAUtil {

    private static final Logger log = LoggerFactory.getLogger(RSAUtil.class);

    /**
     * 默认的key生成方式，根据该参数灵活管理 rsa加密、解密、签名、验签
     */
    static String KEY_GEN_STYLE = "openssl";

    /**
     * key生成方式 - openssl生成
     */
    private static final String KEY_GEN_STYLE_OPENSSL = "openssl";

    /**
     * 生成私钥文件
     *
     * @param privateKeyStr
     * @return
     */
    private static PrivateKey generatePrivateKey(String privateKeyStr) {
        try {
            byte[] buffer = keyToBytes(privateKeyStr, KEY_GEN_STYLE);
            assert buffer != null;
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 私钥加密
     *
     * @param privateKeyStr 私钥字符串
     * @param str           加密原串
     * @return
     * @throws Exception
     */
    public static String encrypt(String privateKeyStr, String str) {
        PrivateKey privateKey = RSAUtil.generatePrivateKey(privateKeyStr);
        if (privateKey == null) {
            throw new RuntimeException("加密私钥为空, 请设置");
        }
        Cipher cipher;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(str.getBytes("UTF-8"));
            // byte数组base64编码后存在非法字符，所以需要再base64编码一次
            return Base64.encodeString(Base64.encodeBytes(output));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("使用默认RSA异常,请检查");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码");
        }
    }

    /**
     * 生成公钥，主要用于openssl生成的key
     *
     * @param publicKeyStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey genPublicKey(String publicKeyStr) {
        try {
            byte[] publicKeyData = Base64.decode(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyData);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.info("公钥生成有误：", e);
            return null;
        }
    }

    /**
     * 生成公钥
     *
     * @param publicKeyStr
     * @param genStyle
     * @return
     */
    public static PublicKey genPublicKey(String publicKeyStr, String genStyle) {
        if (KEY_GEN_STYLE_OPENSSL.equals(genStyle)) {
            return genPublicKey(publicKeyStr);
        }
        return null;
    }

    /**
     * key转byte数组
     *
     * @param keyStr
     * @param genStyle
     * @return
     */
    static byte[] keyToBytes(String keyStr, String genStyle) {
        if (KEY_GEN_STYLE_OPENSSL.equals(genStyle)) {
            return Base64.decode(keyStr);
        }
        return null;
    }


    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥字符串
     * @param encryptStr   密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String publicKeyStr, String encryptStr) {
        //
        if (StringUtils.isBlank(encryptStr)) {
            return "";
        }
        // 因为密文为base64编码后数据，所以需要先base64解码
        encryptStr = Base64.encodeString(encryptStr);
        PublicKey publicKey = genPublicKey(publicKeyStr, KEY_GEN_STYLE);
        if (publicKey == null) {
            throw new RuntimeException("解密公钥为空, 请设置");
        }
        Cipher cipher;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(Base64.decode(encryptStr));
            return new String(output, Charset.forName("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此解密算法");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("使用默认RSA异常,请检查");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("密文长度非法");
        } catch (BadPaddingException e) {
            throw new RuntimeException("密文数据已损坏");
        }
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param byteArr 字节数组
     * @return 十六进制字符串
     */
    static String byteArr2HexString(byte[] byteArr) {
        if (byteArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (byte aByteArr : byteArr) {
            if ((aByteArr & 0xFF) < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(aByteArr & 0xFF, 16));
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转换为字节数组
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    static byte[] hexString2ByteArr(String hexString) {
        if ((hexString == null) || (hexString.length() % 2 != 0)) {
            return new byte[0];
        }
        byte[] dest = new byte[hexString.length() / 2];
        for (int i = 0; i < dest.length; i++) {
            String val = hexString.substring(2 * i, 2 * i + 2);
            dest[i] = (byte) Integer.parseInt(val, 16);
        }
        return dest;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder preStr = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            preStr.append(value);
        }
        return preStr.toString();
    }

}
