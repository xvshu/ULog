package com.el.unity.log.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/11.
 */
public class DateFormatUtils extends org.apache.commons.lang.time.DateFormatUtils {
    public DateFormatUtils() {
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        } else {
            String s = org.apache.commons.lang.time.DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            return s == null ? org.apache.commons.lang.time.DateFormatUtils.format(date, "yyyy-MM-dd") : s;
        }
    }

    public static String format(Timestamp date) {
        return format(date, null);
    }

    public static String format(Timestamp date, String foramt) {
        if (date == null) {
            return null;
        } else {
            return org.apache.commons.lang.time.DateFormatUtils.format(date, null == foramt ? "yyyy-MM-dd" : foramt);
        }
    }

    public static final Date parseDate(String strDate, String format) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(format);
        try {
            date = df.parse(strDate);
        } catch (ParseException var5) {
        }
        return date;
    }

    public static Date formatDate(Date date, String format) {
        SimpleDateFormat inDf = new SimpleDateFormat(format);
        SimpleDateFormat outDf = new SimpleDateFormat(format);
        String reDate = "";
        try {
            reDate = inDf.format(date);
            return outDf.parse(reDate);
        } catch (Exception var6) {
            return date;
        }
    }

}
