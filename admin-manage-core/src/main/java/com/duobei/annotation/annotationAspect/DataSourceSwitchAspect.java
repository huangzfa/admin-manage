package com.duobei.annotation.annotationAspect;

import com.duobei.annotation.DataSourceSwitch;
import com.duobei.common.datasource.DataSourceConst;
import com.duobei.common.datasource.DataSourceHandle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceSwitchAspect {
    @Pointcut("@annotation(com.duobei.annotation.DataSourceSwitch)")
    public void dataSourceSwitchFcuntion(){
    }
    @Around("dataSourceSwitchFcuntion()&&@annotation(dataSourceSwitch)")
    public void beforeSwitch(ProceedingJoinPoint proceedingJoinPoint, DataSourceSwitch dataSourceSwitch ) throws Throwable {
        DataSourceHandle.setDataSourceType(DataSourceConst.OPERATE);
        Object result = null;
        result = proceedingJoinPoint.proceed();
        DataSourceHandle.clearDataSourceType();
    }
}
