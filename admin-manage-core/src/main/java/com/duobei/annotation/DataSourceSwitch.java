package com.duobei.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD,ElementType.TYPE})  //类名或方法上
@Retention(RetentionPolicy.RUNTIME)//运行时
@Documented
public @interface DataSourceSwitch {

    String dataSource();
}
