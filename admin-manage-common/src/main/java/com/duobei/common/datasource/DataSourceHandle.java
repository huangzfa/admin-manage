package com.duobei.common.datasource;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
public class DataSourceHandle {
    private static final ThreadLocal contextHolder = new ThreadLocal(); // 线程本地环境

    // 设置数据源类型
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    // 获取数据源类型
    public static String getDataSourceType() {
        return (String) contextHolder.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
