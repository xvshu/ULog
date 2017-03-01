package com.el.unity.log.client.utils;

import com.alibaba.fastjson.JSON;
import com.el.unity.log.client.aop.UlogEnum;
import com.el.unity.log.client.ump.ULog;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by xvshu on 2016/9/13.
 */
public class UlogUtils {

    public static final String METHOD_SPLIT_TAG = "#";
    /**
     * 根据list创建正则表达式
     * @param packages
     * @return
     */
    public static Pattern createRegex(List<String> packages) {
        if (CollectionUtils.isEmpty(packages)) {
            return null;
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (String str : packages) {
            stringBuffer.append("(^").append(str).append(")*");
        }
        stringBuffer.append("([.][a-zA-Z]+)*");
        return Pattern.compile(stringBuffer.toString());
    }

    public static void initMDCDataMethodEnd(ULog ulog, String className , String methodName, String methodSigner, long methodStart, Object[] params, Object returnValue, Logger logger){
        try{
            long methodEnd = System.currentTimeMillis();
            long methosSpend = methodEnd - methodStart;

            MDC.put(UlogEnum.ClassName.getKey(),className);
            MDC.put(UlogEnum.MethodName.getKey(),methodName);

            MDC.put(UlogEnum.MethosSpend.getKey(),String.valueOf(methosSpend));
            MDC.put(UlogEnum.MethodStart.getKey(), String.valueOf(methodStart));
            MDC.put(UlogEnum.MethodEnd.getKey(),String.valueOf( System.currentTimeMillis()));
            if(ulog!=null && ulog.methodParameter()){
                StringBuilder paramsStr = new StringBuilder();
                for(int i=0 ;i<params.length;i++){
                    paramsStr.append("params[").append(i).append("]:").append( JSON.toJSONString(params[i]).replaceAll("\"", "\'")).append(" ");
                }
                MDC.put(UlogEnum.MethodParameter.getKey(),paramsStr.toString());
            }else{
                MDC.put(UlogEnum.MethodParameter.getKey(),"no config");
            }
            if(ulog!=null && ulog.returnValue()) {
                MDC.put(UlogEnum.ReturnValue.getKey(),JSON.toJSONString(returnValue).replaceAll("\"", "\'"));
            }else{
                MDC.put(UlogEnum.ReturnValue.getKey(),"no config");
            }

            boolean isBegin = false;
            //如果是访问链开始方法
            if (MDC.get(UlogEnum.AopPointClassName.getKey()) != null) {
                String MdcMethosStr = MDC.get(UlogEnum.AopPointClassName.getKey());
                if (MdcMethosStr.equals(className + METHOD_SPLIT_TAG + methodName+ METHOD_SPLIT_TAG +methodSigner)) {
                    isBegin=true;
                }
            }
            MDC.put(UlogEnum.IsBeginMethod.getKey(),isBegin?"yes":"no");
        }catch (Throwable e){
            logger.error("=initMDCDataMethodEnd=>error:{}",ExceptionUtils.getMsg(e));
        }
    }

    public static String initMDCDataMethodStart(String className, String methodName,String methodSigner, Logger logger) {
        try {
            String newid = IDUtils.getID();
            if (MDC.get(UlogEnum.AopPointClassName.getKey()) == null || MDC.get(UlogEnum.AopPointClassName.getKey()).isEmpty()) {
                MDC.put(UlogEnum.AopPointClassName.getKey(), className + METHOD_SPLIT_TAG + methodName + METHOD_SPLIT_TAG +methodSigner);
            }
            if (MDC.get(UlogEnum.TreeID.getKey()) == null || MDC.get(UlogEnum.TreeID.getKey()).isEmpty()) {
                MDC.put(UlogEnum.TreeID.getKey(), newid);
            }
            if (MDC.get(UlogEnum.RequestId.getKey()) == null || MDC.get(UlogEnum.RequestId.getKey()).isEmpty()) {
                MDC.put(UlogEnum.RequestId.getKey(), newid);
            }
            MDC.put(UlogEnum.TransactionId.getKey(), newid);
            return newid;
        } catch (Throwable e) {
            logger.error("=initMDCDataMethodStart=>error:{}", ExceptionUtils.getMsg(e));
        }
        return null;
    }

    public static void writeLog(String className ,String methodName,String methodSigner,Exception exception, Logger logger){
        try{
            //打印性能日志（日志为拼接Json字符串）
            if(exception==null){
                logger.info("<=" + methodName + "=>performsfile log is end");
            }else{
                logger.error("=" + methodName + "=>has error:{}",ExceptionUtils.getMsg(exception));
            }

            //移除执行方法TreeID，及进入log标记AopPointClassName
            if (MDC.get(UlogEnum.AopPointClassName.getKey()) != null &&
                    MDC.get(UlogEnum.AopPointClassName.getKey()).equals(className + METHOD_SPLIT_TAG + methodName+ METHOD_SPLIT_TAG +methodSigner)) {
                MDC.clear();
            }
        }catch (Throwable e){
            logger.error("=writeLog=>error:{}", ExceptionUtils.getMsg(e));
        }
    }

}
