package com.el.unity.log.service;

/**
 * Created by xvshu on 2016/6/14.
 */
public class TestGetClassNames {
    public static void fun() {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        System.out.println(stack[2].getClassName());
        System.out.println(stack[2].getMethodName());

    }


}

class Test {
    public static void main(String[] args) throws Exception {
        TestGetClassNames.fun();
    }
}
