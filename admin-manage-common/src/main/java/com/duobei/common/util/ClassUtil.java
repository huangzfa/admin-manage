package com.duobei.common.util;

import java.lang.reflect.Field;

public class ClassUtil {
    public static Field getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        }
        return field;

    }

    public static String getClassValueByClassName(String fieldName, Object object){
        try {

            Field field = getFieldByClasss(fieldName,object);
            if (field == null){
                return null;
            }
            field.setAccessible(true);
            return field.get(object).toString();
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
