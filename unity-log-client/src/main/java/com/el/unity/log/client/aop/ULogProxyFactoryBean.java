package com.el.unity.log.client.aop;

import com.el.unity.log.client.switcher.LogSwitcher;
import com.el.unity.log.client.ump.ULog;
import com.el.unity.log.client.utils.ExceptionUtils;
import com.el.unity.log.client.utils.UlogUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/7.
 */
public class ULogProxyFactoryBean extends AbstractAutoProxyCreator implements MethodInterceptor {


    private List<String> packages;//通过的包名
    private List<String> excludePackages;//不通过的包名
    private Pattern includePackageRegex;//包含的正则表达式
    private Pattern excludePackageRegex;//不包含的正则表达式
    private List<String> includeMethods;//通过的方法名
    private List<String> excludeeMethods;//不通过的方法名
    private Pattern includeMethodsRegex;//包含的正则表达式
    private Pattern excludeMethodsRegex;//不包含的正则表达式
    private final Logger logger = LogManager.getLogger(ULogProxyFactoryBean.class.getName());
    private String log4jDomainValues;

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        try {
            if (!CollectionUtils.isEmpty(packages)) {
                if (Proxy.isProxyClass(beanClass)) {
                    Class<?>[] interfaces = beanClass.getInterfaces();
                    for (Class interClzz : interfaces) {
                        if (interClzz.getPackage() == null) {
                            continue;
                        }
                        String className = interClzz.getName();
                        if (regexMatch(className, includePackageRegex) && !regexMatch(className, excludePackageRegex)) {
                            return new Object[]{this};
                        }
                    }
                } else {
                    if (null != beanClass.getPackage()
                            && regexMatch(beanClass.getPackage().getName(), includePackageRegex)
                            && !regexMatch(beanClass.getPackage().getName(), excludePackageRegex)) {
                        return new Object[]{this};
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("=getAdvicesAndAdvisorsForBean=>error:{}", ExceptionUtils.getMsg(ex));
            return DO_NOT_PROXY;
        }
        return DO_NOT_PROXY;
    }


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(invocation.getThis());

        //disconf控制性能日志开关
        if ("true".equals(LogSwitcher.getLog4jSync().trim())) {
            ULog log = targetClass.getAnnotation(ULog.class);   //验证类注解
            if (null == log) {
                log = targetClass.getMethod(invocation.getMethod().getName(), invocation.getMethod().getParameterTypes()).getAnnotation(ULog.class);
            }
            if (log == null) { //验证方法注解
                return monitorLog(null, invocation);
            } else if (log.log()) {
                return monitorLog(log, invocation);
            }
        }
        return invocation.proceed();
    }

    /**
     * ulog2.0log生成
     *
     * @param uLog
     * @param invocation
     * @return
     * @throws Throwable
     */
    private Object monitorLog(ULog uLog, MethodInvocation invocation) throws Throwable {
        Object result = null;
        Exception exception = null;
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(invocation.getThis());
        String className = targetClass.getName();
        String methodName = invocation.getMethod().getName();
        invocation.getMethod().getParameterTypes().getClass().getSigners();
        Class<?>[] paramClasses=  invocation.getMethod().getParameterTypes();
        StringBuilder methodClasses= new StringBuilder();
        if (null != paramClasses) {
            for (Class clazz : paramClasses) {
                methodClasses.append(clazz.getSimpleName());
            }
        }

        //进行性能日志处理----------start----------
        //正则验证包含方法集合
        if (includeMethodsRegex != null && !(regexMatch(className + "." + methodName, includeMethodsRegex))) {
            return invocation.proceed();
        }

        //正则验证不包含方法集合
        if (excludeMethodsRegex != null && regexMatch(className + "." + methodName, excludeMethodsRegex)) {
            return invocation.proceed();
        }
        String transactionId = UlogUtils.initMDCDataMethodStart(className, methodName, methodClasses.toString(),logger);
        long methodStart = System.currentTimeMillis();
        try {
            result = invocation.proceed();
        } catch (Exception ex) {
            exception = ex;
        }
        MDC.put(UlogEnum.TransactionId.getKey(), transactionId);
        UlogUtils.initMDCDataMethodEnd(uLog, className, methodName, methodClasses.toString(), methodStart, invocation.getArguments(), result,logger);
        UlogUtils.writeLog(className, methodName,methodClasses.toString(), exception,logger);
        if(null!=exception){
            throw exception;
        }
        return result;
    }


    public void setExcludePackages(List<String> excludePackages) {
        this.excludePackages = excludePackages;
        excludePackageRegex = UlogUtils.createRegex(excludePackages);
    }

    private boolean regexMatch(String pkName, Pattern checkRegex) {
       //如果没有设置包含的话，则默认为所有的
        return checkRegex != null && checkRegex.matcher(pkName).matches();
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
        includePackageRegex = UlogUtils.createRegex(packages);
    }

    public void setIncludeMethods(List<String> includeMethods) {
        this.includeMethods = includeMethods;
        includeMethodsRegex = UlogUtils.createRegex(includeMethods);
    }

    public void setExcludeeMethods(List<String> excludeeMethods) {
        this.excludeeMethods = excludeeMethods;
        excludeMethodsRegex = UlogUtils.createRegex(excludeeMethods);
    }

    public void setLog4jDomainValues(String log4jDomainValues) {
        this.log4jDomainValues = log4jDomainValues;
    }
}
