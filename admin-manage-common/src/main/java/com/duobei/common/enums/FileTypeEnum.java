package com.duobei.common.enums;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件类型枚举
 * 
 * @Description TODO
 * @author tluo
 * @date 2018年11月16日
 */
public enum FileTypeEnum {
	/** jpg文件 **/
	JPEG("ffd8ffe000104a464946", "jpg"),
	/** png文件 **/
	PNG("89504e470d0a1a0a0000", "png"),
	/** gif文件 **/
	GIF("47494638396126026f01", "gif"),
	/** tif文件 **/
	TIFF("49492a00227105008037", "tif"),
	/** 16色位图 **/
	BMP16("424d228c010000000000", "bmp"),
	/** 24位位图 **/
	BMP24("424d8240090000000000", "bmp"),
	/** 256色位图 **/
	BMP256("424d8e1b030000000000", "bmp"),
	/** CAD文件 **/
	DWG("41433130313500000000", "dwg"),
	/** html文件 **/
	HTML("3c21444f435459504520", "html"),
	/** htm文件 **/
	HTM("3c21646f637479706520", "htm"),
	/** css文件 **/
	CSS("48544d4c207b0d0a0942", "css"),
	/** js文件 **/
	JS("696b2e71623d696b2e71", "js"),
	/** Rich Text Format **/
	RTF("7b5c727466315c616e73", "rtf"),
	/** Photoshop文件 **/
	PSD("38425053000100000000", "psd"),
	/** EMAIL文件 **/
	EML("46726f6d3a203d3f6762", "eml"),
	/** MS Excel 注意：word、msi 和 excel的文件头一样 **/
	DOC("d0cf11e0a1b11ae10000", "doc"),
	/** Visio 绘图 **/
	VSD("d0cf11e0a1b11ae10000", "vsd"),
	/** MS Access (mdb) **/
	MDB("5374616E64617264204A", "mdb"),
	/****/
	PS("252150532D41646F6265", "ps"),
	/** Adobe Acrobat (pdf) **/
	PDF("255044462d312e350d0a", "pdf"),
	/** rmvb/rm相同 **/
	RMVB("2e524d46000000120001", "rmvb"),
	/** flv与f4v相同 **/
	FLV("464c5601050000000900", "flv"),
	/****/
	MP4("00000020667479706d70", "mp4"),
	/****/
	MP3("49443303000000002176", "mp3"),
	/****/
	MPG("000001ba210001000180", "mpg"),
	/** wmv与asf相同 **/
	WMV("3026b2758e66cf11a6d9", "wmv"),
	/** Wave (wav) **/
	WAV("52494646e27807005741", "wav"),
	/****/
	AVI("52494646d07d60074156", "avi"),
	/** MIDI (mid) **/
	MID("4d546864000000060001", "mid"),
	/****/
	ZIP("504b0304140000000800", "zip"),
	/****/
	RAR("526172211a0700cf9073", "rar"),
	/****/
	INI("235468697320636f6e66", "ini"),
	/****/
	JAR("504b03040a0000000000", "jar"),
	/** 可执行文件 **/
	EXE("4d5a9000030000000400", "exe"),
	/** jsp文件 **/
	JSP("3c25402070616765206c", "jsp"),
	/** MF文件 **/
	MF("4d616e69666573742d56", "mf"),
	/** xml文件 **/
	XML("3c3f786d6c2076657273", "xml"),
	/** xml文件 **/
	SQL("494e5345525420494e54", "sql"),
	/** java文件 **/
	JAVA("7061636b616765207765", "java"),
	/** bat文件 **/
	BAT("406563686f206f66660d", "bat"),
	/** gz文件 **/
	GZ("1f8b0800000000000000", "gz"),
	/****/
	PROPERTIES("6c6f67346a2e726f6f74", "properties"),
	/****/
	CLASS("cafebabe0000002e0041", "class"),
	/****/
	CHM("49545346030000006000", "chm"),
	/****/
	MXP("04000000010000001300", "mxp"),
	/****/
	DOCX("504b0304140006000800", "docx"),
	/** WPS文字wps、表格et、演示dps都是一样的 **/
	WPS("d0cf11e0a1b11ae10000", "wps"),
	/****/
	TORRENT("6431303a637265617465", "torrent");

	private String header;
	private String type;

	private FileTypeEnum(String header, String type) {
		this.header = header;
		this.type = type;
	}

	public String getHeader() {
		return header;
	}

	public String getType() {
		return type;
	}

	/**
	 * 判断文件是否为指定类型
	 * 
	 * @author tluo
	 * @date 2018年11月16日
	 * @param
	 * @param typeEnum
	 * @return
	 * @throws
	 */
	public static boolean isFileType(InputStream in, FileTypeEnum typeEnum) {
		if (null == typeEnum) {
			return false;
		}
		FileTypeEnum type = getFileType(in);
		if (null == type || !type.getHeader().equals(typeEnum.getHeader())) {
			return false;
		}
		return true;

	}

	/**
	 * 根据制定文件的文件头判断其文件类型
	 * 
	 * @param is
	 * @return
	 */
	public static FileTypeEnum getFileType(InputStream is) {
		FileTypeEnum res = null;
		try {
			byte[] b = new byte[10];
			is.read(b, 0, b.length);
			is.close();
			String fileCode = bytesToHexString(b);

			for (FileTypeEnum type : FileTypeEnum.values()) {
				if (type.getHeader().toLowerCase().startsWith(fileCode.toLowerCase())
						|| fileCode.toLowerCase().startsWith(type.getHeader().toLowerCase())) {
					res = type;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	/**
	 * 得到上传文件的文件头
	 * 
	 * @param src
	 * @return
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
