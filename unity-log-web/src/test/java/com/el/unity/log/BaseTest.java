package com.el.unity.log;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zscome on 15-4-26.
 */
public abstract class BaseTest {
    protected static ApplicationContext appContext;

    @BeforeClass
    public static void setUp() throws Exception {
        try {
            long start = System.currentTimeMillis();
            System.out.println("正在加载配置文件...");
            appContext = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
            System.out.println("配置文件加载完毕,耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            BaseTest.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(appContext.getBean("taskProvider"));
    }

    protected boolean setProtected() {
        return false;
    }

    @Before
    public void autoSetBean() {
        appContext.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }
}
