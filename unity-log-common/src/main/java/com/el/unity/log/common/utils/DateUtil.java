package com.el.unity.log.common.utils;

import com.el.common.utils.ReflectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zscome on 15-4-26.
 */
public class DateUtil extends DateUtils {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Date currentDate = DateUtil.createDate("2015-05-03 12:01:01", YYYYMMDD_HHMMSS);
        Date d = DateUtil.getAfterHoursDate(currentDate, 1);
        System.out.println(DateUtil.format2AddFirstTime(new Timestamp(d.getTime())));
        System.out.println(DateUtil.format2AddLastTime(new Timestamp(d.getTime())));
    }

    protected static  final Logger logger = LogManager.getLogger(DateUtil.class.getName());
    private boolean esClientTransportSniff;  //探测集群中机器状态
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDD_000000 = "yyyy-MM-dd 00:00:00";
    public static final String YYYYMMDD_235959 = "yyyy-MM-dd 23:59:59";

    public static Date getNowDate() {
        return Calendar.getInstance().getTime();
    }

    public static int getNowDayOfWeek() {
        int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return index == 1 ? 7 : index - 1;
    }

    public static int getNowDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static Timestamp getNowTime() {
        return new Timestamp(getNowDate().getTime());
    }


    public static Timestamp getNowTime(long time) {
        return new Timestamp(time);
    }

    public static Date getAfterHoursDate(Date currentDate, int hours) {
        Date returnDate = null;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.HOUR, hours);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        try {
            returnDate = sdf.parse(sdf.format(d));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnDate;
    }

    /**
     * 获取一个时间范围内的所有日期
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<Date> getDates(Date beginDate, Date endDate) {
        beginDate = formatToDate(beginDate, YYYYMMDD);
        endDate = formatToDate(endDate, YYYYMMDD);
        List<Date> dates = new ArrayList<Date>();
        ;
        if (endDate.compareTo(beginDate) > 0) {
            Date index = formatToDate(beginDate, YYYYMMDD);
            while (index.compareTo(endDate) <= 0) {
                dates.add(index);
                index = getTomorrow(index);
            }
        } else if (endDate.compareTo(beginDate) == 0) {
            dates.add(formatToDate(beginDate, YYYYMMDD));
        }
        return dates;
    }


    /**
     * 获取输入日期的前一个月
     *
     * @param currentDate 输入日期
     * @return 输入日期的前一个月的日期
     */
    public static Date getDateBeforeMonth(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        return createDate(sdf.format(d), YYYYMMDD);
    }

    /**
     * 获取输入日期的前一天
     *
     * @param currentDate 输入日期
     * @return 输入日期的前一天
     */
    public static Date getYesterday(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, -1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        return createDate(sdf.format(d), YYYYMMDD);
    }

    /**
     * 获取输入日期的后一天
     *
     * @param currentDate 输入日期
     * @return 输入日期的前一天
     */
    public static Date getTomorrow(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        return createDate(sdf.format(d), YYYYMMDD);
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param currentDate 输入日期
     * @return 输入日期的当月第一天
     */
    public static Date getFirstDayOfMonth(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.DATE, 1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        return createDate(sdf.format(d), YYYYMMDD);
    }

    /**
     * 将时间格式的字符串，转化为Date
     *
     * @param dateFormat exp: 2010-7-22
     * @return java.util.Date
     */
    public static Date createDate(String dateString, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date d = null;
        try {
            d = sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error("字符串转化为Date失败,[string=" + dateString + "]", e);
        }
        return d;
    }

    /**
     * 将时间转化为指定格式的字符串
     *
     * @param date   输入日期
     * @param format 日期格式
     * @return 字符串格式的日期
     * @author lishuai
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatAddTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date) + " 23:59:59";
    }

    public static String format2AddLastTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_235959);
        return sdf.format(parseDate(date, YYYYMMDD));
    }

    public static String format2AddFirstTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_000000);
        return sdf.format(parseDate(date, YYYYMMDD));
    }

    public static Timestamp format2AddLastTime(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_235959);
        return new Timestamp(parseDate(sdf.format(date), YYYYMMDD_HHMMSS).getTime());
    }

    public static Timestamp format2AddFirstTime(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_000000);
        return new Timestamp(parseDate(sdf.format(date), YYYYMMDD_HHMMSS).getTime());
    }

    public static <T> T formatObjectTime(T t) {
        Object value;
        for (Field field : ReflectUtils.getAccessibleField(t)) {
            if (Timestamp.class.isAssignableFrom(field.getType()) && (field.getName().endsWith("Start") || field.getName().endsWith("End"))) {
                value = ReflectUtils.getFieldValue(t, field.getName());
                if (null != value) {
                    if (field.getName().endsWith("Start")) {
                        ReflectUtils.setFieldValue(t, field.getName(), format2AddFirstTime((Timestamp)value));
                    } else {
                        ReflectUtils.setFieldValue(t, field.getName(), format2AddLastTime((Timestamp)value));
                    }
                }
            }
        }
        return t;
    }

    public static Date parseDate(String str, String... parsePatterns) {
        try {
            return DateUtils.parseDate(str, parsePatterns);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseTime(String str, String... parsePatterns) {
        try {
            return new Timestamp(DateUtils.parseDate(str, parsePatterns).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date formatToDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return createDate(sdf.format(date), format);
    }

}
