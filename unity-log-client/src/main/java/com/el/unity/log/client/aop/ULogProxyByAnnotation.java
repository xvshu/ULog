package com.el.unity.log.client.aop;

import com.el.unity.log.client.ump.ULog;
import com.el.unity.log.client.utils.UlogUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/4/7.
 */
@Aspect
@Service
public class ULogProxyByAnnotation  {

    private final Logger logger = LogManager.getLogger(ULogProxyByAnnotation.class.getName());

    @Pointcut("@within(com.el.unity.log.client.ump.ULog) ||" +
            " @annotation(com.el.unity.log.client.ump.ULog)")
    private void aspectjMethod(){};

    public ULogProxyByAnnotation(){}

    @Around("aspectjMethod()")
    public Object cache(ProceedingJoinPoint point) throws Throwable {
        Exception exception = null;
        String className=point.getTarget().getClass().getSimpleName();
        String methodName=point.getSignature().getName();
        Method method = ((MethodSignature)MethodSignature.class.cast(point.getSignature())).getMethod();

        Class<?>[] paramClasses=  method.getParameterTypes();
        StringBuilder methodClasses= new StringBuilder();
        if (null != paramClasses) {
            for (Class clazz : paramClasses) {
                methodClasses.append(clazz.getSimpleName());
            }
        }
        ULog uLog = (ULog)method.getAnnotation(ULog.class);
        Object res = null;


        String transactionId = UlogUtils.initMDCDataMethodStart(className, methodName, methodClasses.toString(),logger);
        long methodStart = System.currentTimeMillis();

        try {
            res = point.proceed();
        } catch (Exception ex) {
            exception = ex;
        }

        MDC.put(UlogEnum.TransactionId.getKey(), transactionId);
        UlogUtils.initMDCDataMethodEnd(uLog, className, methodName, methodClasses.toString(), methodStart, point.getArgs(), res,logger);
        UlogUtils.writeLog(className, methodName,methodClasses.toString(), exception,logger);
        if(null!=exception){
            throw exception;
        }
        return res;
    }

}
