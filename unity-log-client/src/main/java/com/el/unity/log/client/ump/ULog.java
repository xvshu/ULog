package com.el.unity.log.client.ump;



import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/4/6.
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ULog {
    /**
     * Transaction Parameter type;
     *
     * @return
     */
    String type() default "";

    /**
     * Transaction Parameter name;
     *
     * @return
     */
    String name() default "";

    /**
     * is add monitor log or not .
     *
     * @return
     */
    boolean log() default true;

    boolean methodParameter() default false;

    boolean returnValue() default false;

}
