package com.duobei.common.util;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ConfigProperties implements BeanFactoryPostProcessor {

    private static Properties config = new Properties();

    private String configPath = null;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void init() {
        try {
            config.load(new InputStreamReader(ConfigProperties.class.getClassLoader().getResourceAsStream(configPath), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public static Properties getConfig() {
        return config;
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the string
     */
    public static String get(String key) {
        return config.getProperty(key);
    }

    /**
     * Gets the.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String get(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    @Override
    public void postProcessBeanFactory(
        ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.init();
    }

}
