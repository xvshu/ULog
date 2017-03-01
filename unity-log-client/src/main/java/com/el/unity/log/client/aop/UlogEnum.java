package com.el.unity.log.client.aop;

/**
 * Created by xvshu on 2016/7/12.
 */
public enum UlogEnum {
    ClassName("cname"),
    MethodName("mname"),
    TreeID("teid"),    //调用过程中跨域唯一id
    RequestId("rtid"),  //调用过程中每个域内唯一id
    TransactionId("tnid"),  //调用过程中每个域内方法id
    MethosSpend("mspen"),    //调用过程中每个域内方法消费时间
    MethodStart("mstart"),
    MethodEnd("mend"),
    MethodParameter("mparam"),
    ReturnValue("return"),
    IsBeginMethod("misbegin"),
    AopPointClassName("AopPointClassName"); //AOP切面第一次切入的类与方法

    private String key;
    UlogEnum(String key){
        this.key=key;
    }

    public String getKey() {
        return key;
    }
}
