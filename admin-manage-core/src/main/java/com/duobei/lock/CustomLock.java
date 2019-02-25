package com.duobei.lock;

/**
 * @author huangzhongfa
 * @description 全局锁，包括锁的名称
 * @date 2018/12/4 15:31
 */

public class CustomLock {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
