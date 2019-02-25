package com.duobei.common.datasource;

import org.springframework.core.NamedThreadLocal;

public class DynamicDataSourceSwitch {
	private static final ThreadLocal<String> DATA_SOURCE_MARKER = new NamedThreadLocal<String>("datasource marker");
	private static final String MASTER = "master";
	private static final String SLAVE = "slave";

	public static String getDataSource() {
		return (String) DATA_SOURCE_MARKER.get();
	}

	private static void setDataSource(String dataSourceMarker) {
		DATA_SOURCE_MARKER.set(dataSourceMarker);
	}

	public static void setMaster() {
		setDataSource(MASTER);
	}

	public static void setSlave() {
		setDataSource(SLAVE);
	}

	public static void clearDataSource() {
		DATA_SOURCE_MARKER.remove();
	}

	public static boolean isMaster() {
		return MASTER.equalsIgnoreCase(getDataSource());
	}

	public static boolean isSlave() {
		return SLAVE.equalsIgnoreCase(getDataSource());
	}
}
