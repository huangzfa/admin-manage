package com.duobei.common.annotation.annotationAspect;

import com.duobei.common.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceHandle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 火柴
 * @description 数据源切换切面类
 * @date 2019/2/27
 */
@Aspect
@Component
public class DataSourceSwitchAspect {
    @Pointcut("@annotation(com.duobei.common.annotation.DataSourceSwitch)")
    public void dataSourceSwitchFcuntion() {
    }

    @Around("dataSourceSwitchFcuntion()&&@annotation(dataSourceSwitch)")
    public void beforeSwitch(ProceedingJoinPoint proceedingJoinPoint, DataSourceSwitch dataSourceSwitch) throws Throwable {
        DataSourceHandle.setDataSourceType(dataSourceSwitch.dataSource());
        Object result = null;
        result = proceedingJoinPoint.proceed();
        DataSourceHandle.clearDataSourceType();
    }
}
