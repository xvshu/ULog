package com.el.unity.log.client.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xvshu on 2016/8/9.
 */
public class ExceptionUtils {

    public static String getMsg(Throwable error) {
        PrintWriter out = null;
        try {
            StringWriter sw = new StringWriter();
            out = new PrintWriter(sw);
            error.printStackTrace(out);
            String returnStr = sw.toString().replaceAll("[\\t\\n\\r]", "<br>").replaceAll("<br><br>","<br>");
            return  returnStr;
        } catch (Exception e) {
            return "bad getErrorMap";
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

}
