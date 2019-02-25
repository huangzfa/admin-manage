package com.duobei.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	
	private Properties properties = null;
	private static Map<String, PropertiesUtil> instanceMap = new HashMap<String, PropertiesUtil>();
	private String propertyFileName;
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	protected PropertiesUtil(String propertyFileName) {
		this.propertyFileName = propertyFileName;
		loadProperties();
	}

	public static PropertiesUtil getInstance(String propertyFileName) {
		if (instanceMap.get(propertyFileName) != null) {
			return instanceMap.get(propertyFileName);
		}

		PropertiesUtil instance = new PropertiesUtil(propertyFileName);
		instanceMap.put(propertyFileName, instance);

		return instance;
	}

	public String getPropertyAsString(String propertyName, String defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}
			return properties.getProperty(propertyName, defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public String getPropertyAsString(String propertyName) {
		return getPropertyAsString(propertyName, null);
	}
	public int getPropertyAsInt(String propertyName, int defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}

			String sProperty = properties.getProperty(propertyName);

			return Integer.parseInt(sProperty);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public int getPropertyAsInt(String propertyName) {
		return getPropertyAsInt(propertyName, 0);
	}

	public byte getPropertyAsByte(String propertyName, byte defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}

			String sProperty = properties.getProperty(propertyName);

			return Byte.parseByte(sProperty);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public byte getPropertyAsByte(String propertyName) {
		return getPropertyAsByte(propertyName, (byte) 0);
	}

	public double getPropertyAsDouble(String propertyName, double defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}

			String sProperty = properties.getProperty(propertyName);

			return Double.parseDouble(sProperty);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public double getPropertyAsDouble(String propertyName) {
		return getPropertyAsDouble(propertyName, 0);
	}

	public long getPropertyAsLong(String propertyName, long defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}

			String sProperty = properties.getProperty(propertyName);
			return Long.parseLong(sProperty);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public long getPropertyAsLong(String propertyName) {
		return getPropertyAsLong(propertyName, 0l);
	}

	protected void loadProperties() {
		try {
			properties = new Properties();

			ClassLoader oClassLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = oClassLoader.getResourceAsStream(propertyFileName + ".properties");

			if (is != null) {
				properties.load(is);
				is.close();
			}

			is = null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public Properties getProperties() {
		return properties;
	}
}
