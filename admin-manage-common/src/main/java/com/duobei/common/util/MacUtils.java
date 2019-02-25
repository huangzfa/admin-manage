package com.duobei.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacUtils {
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	public static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");

				if (index != -1) {
					mac = line.substring(index + "hwaddr".length() + 1).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	public static String getLinuxMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("\u786C\u4EF6\u5730\u5740");

				if (index != -1) {
					mac = line.substring(index + 4).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		if (mac == null) {
			return getUnixMACAddress();
		}

		return mac;
	}

	public static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.split("-").length == 6) {
					index = line.indexOf(":");
					if (index == -1) {
						break;
					}
					mac = line.substring(index + 1).trim();

					break;
				}
				index = line.toLowerCase().indexOf("\u7269\u7406\u5730\u5740");
				if (index != -1) {
					index = line.indexOf(":");
					if (index == -1) {
						break;
					}
					mac = line.substring(index + 1).trim();

					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	public static String getMac() {
		String os = getOSName();
		String mac;
		if (os.startsWith("windows")) {
			mac = getWindowsMACAddress();
		} else {
			if (os.startsWith("linux"))
				mac = getLinuxMACAddress();
			else
				mac = getUnixMACAddress();
		}
		return mac == null ? "" : mac;
	}

	public static void main(String[] argc) {
		String os = getOSName();
		System.out.println("os: " + os);
		if (os.startsWith("windows")) {
			String mac = getWindowsMACAddress();
			System.out.println("mac: " + mac);
		} else if (os.startsWith("linux")) {
			String mac = getLinuxMACAddress();
			System.out.println("mac: " + mac);
		} else {
			String mac = getUnixMACAddress();
			System.out.println("mac: " + mac);
		}
	}
}
