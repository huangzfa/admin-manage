package com.duobei.common.util.lang;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 生成uuid
	 * @return
	 */
	public static String getUUID() {   
        return UUID.randomUUID().toString().replace("-", "");   
    }   
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getUUID());
		}
	}
}
