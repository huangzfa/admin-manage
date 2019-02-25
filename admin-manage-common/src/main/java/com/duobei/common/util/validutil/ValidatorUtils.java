package com.duobei.common.util.validutil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 验证工具
 *
 * @author: ritchey
 * @date: 2019/1/24
 * @time: 20:58
 * @version: v1.0
 * Description:
 */
public class ValidatorUtils {

    private static Logger logger = LoggerFactory.getLogger(ValidatorUtils.class);

    /**
     * 工具校验  校验类属性不能为空
     * @param obj
     * @param cla
     * @return
     */
    public static String validClassNotNull(Object obj,Class cla){
        Field[] fields = cla.getDeclaredFields();
        for(Field field:fields){
            if(field.getAnnotation(AttributNotNull.class) != null){
                String name = field.getName();
                StringBuilder sb = new StringBuilder();
                sb.append("get").append(name.substring(0,1).toUpperCase()).append(name.substring(1,name.length()));
                try {
                    Method method = cla.getMethod(sb.toString());
                    if (field.getType().isAssignableFrom(String.class)){
                        Object value = method.invoke(obj);
                        if(value ==null || StringUtils.isEmpty(value.toString())){
                            return field.getName() + "参数不能为null!";
                        }
                    }else{
                        method.invoke(obj);
                        Object value = method.invoke(obj);
                        if( value == null){
                            return field.getName() + "参数不能为null!";
                        }
                    }
                } catch (Exception e) {
                    logger.error("反射取值校验数据时发生异常!",e);
                    return "反射取值校验数据时发生异常!";
                }
            }
        }
        return "";
    }




}
