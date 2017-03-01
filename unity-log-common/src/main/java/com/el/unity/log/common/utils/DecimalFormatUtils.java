package com.el.unity.log.common.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/4/27.
 */
public class DecimalFormatUtils {
    public DecimalFormatUtils() {
    }

    public static String format(double d) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(d);
    }

    public static String format(long i) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(i);
    }

    public static String format(double d, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(d);
    }

    public static String format(long i, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(i);
    }

    public static void main(String[] args) {
        System.out.println(format(-2.34234342343434E10D));
        long i = 34234234234234L;
        System.out.println(format(i));
    }
}
