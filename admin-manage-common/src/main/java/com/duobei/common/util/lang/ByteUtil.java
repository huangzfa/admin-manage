package com.duobei.common.util.lang;

/**
 * Byte常用操作
 * 
 * @author JingChenglong 2018/11/05 10:57
 *
 */
public class ByteUtil {

	/**
	 * 根据限定的每组的字节长度，将字节数组分组
	 * 
	 * @param bytes
	 *            待拆分的字节数组
	 * @param splitLength
	 *            每组字节长度
	 * @return 分组后的字节组
	 */
	public static byte[][] splitBytes(byte[] bytes, int splitLength) {

		// 计算bytes与splitLength的余数，以便算出要拆分的组数
		int remainder = bytes.length % splitLength;

		// 数组拆分后的组数，如果余数不为0则加1
		int quotient = 0 == remainder ? bytes.length / splitLength : bytes.length / splitLength + 1;

		byte[][] arrays = new byte[quotient][];
		byte[] metaArray = null;

		for (int i = 0; i < quotient; i++) {
			// 如果是最后一组(quotient-1),同时余数不等于0,就将最后一组设置为remainder的长度
			if (i == quotient - 1 && remainder != 0) {
				metaArray = new byte[remainder];
				System.arraycopy(bytes, i * splitLength, metaArray, 0, remainder);
			} else {
				metaArray = new byte[splitLength];
				System.arraycopy(bytes, i * splitLength, metaArray, 0, splitLength);
			}
			arrays[i] = metaArray;
		}
		return arrays;
	}
}