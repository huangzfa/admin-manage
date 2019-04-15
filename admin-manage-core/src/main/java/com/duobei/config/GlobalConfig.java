package com.duobei.config;

import com.duobei.common.config.Global;

public class GlobalConfig {
	/**
	 * 管理员账号
	 * 
	 * @return
	 */
	public static String getSuperAdmin() {
		return Global.getValAsString("superAdmin");
	}

	/**
	 * 每页数量
	 * 
	 * @return
	 */
	public static int getPageSize() {
		return Global.getValAsInt("pageSize", 10);
	}

	/**
	 * 权限控制路径
	 * 
	 * @return
	 */
	public static String getAuthzPath() {
		return Global.getValAsString("authzPath");
	}

	/**
	 * 维护系统首页路径
	 * 
	 * @return
	 */
	public static String getIndexPath() {
		return Global.getValAsString("indexPath");
	}

	/**
	 * 是否开发环境
	 * 
	 * @return
	 */
	public static boolean isDevEnvironment() {
		return "dev".equals(Global.getValAsString("environment"));
	}

	/**
	 * 是否测试环境
	 *
	 * @return
	 */
	public static boolean isTestEnvironment() {
		return "test".equals(Global.getValAsString("environment"));
	}

	/**
	 * 是否测试环境
	 *
	 * @return
	 */
	public static boolean isPreviewEnvironment() {
		return "preview".equals(Global.getValAsString("environment"));
	}


	/**
	 * 是否生产环境
	 * 
	 * @return
	 */
	public static boolean isProdEnvironment() {
		return "prod".equals(Global.getValAsString("environment"));
	}
}
