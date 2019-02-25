package com.duobei.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by 15935 on 2018/12/6.
 */
public class BeanUtil {
    private static WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

    public BeanUtil() {
    }

    public static Object getBean(String name) {
        return ctx.getBean(name);
    }

    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        }
    }
