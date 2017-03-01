package com.el.unity.log.client.switcher;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * o(>﹏<)o不要啊
 *
 * @User : Hapic
 * @Date : 2016/3/23 16:Deal21
 */
@DisconfFile(filename = "logSwitcher.properties")
public class LogSwitcher {

    /*public static final String log4jSys = "log4jSys";
    public static final String discountRate = "discountRate";*/


    private static String log4jSync="true";//log4j默认是否启动采集
    private static String logbackSync="true";//logback默认是否启动采集

    private static Integer capacity=10000;//异常采集的最大数量开始写到flume里面

//    private static String reconnect="true";//是否进行连接重试



    @DisconfFileItem(name = "log4jSync", associateField = "log4jSync")
    public static String getLog4jSync() {
        return log4jSync;
    }

    public static void setLog4jSync(String log4jSync) {
        LogSwitcher.log4jSync = log4jSync;
    }

    @DisconfFileItem(name = "logbackSync", associateField = "logbackSync")
    public static String getLogbackSync() {
        return logbackSync;
    }

    public static void setLogbackSync(String logbackSync) {
        LogSwitcher.logbackSync = logbackSync;
    }

    @DisconfFileItem(name = "capacity", associateField = "capacity")
    public static Integer getCapacity() {
        return capacity;
    }

    public static void setCapacity(Integer capacity) {
        LogSwitcher.capacity = capacity;
    }

   /* @DisconfFileItem(name = "reconnect", associateField = "reconnect")
    public static String getReconnect() {
        return reconnect;
    }

    public static void setReconnect(String reconnect) {
        LogSwitcher.reconnect = reconnect;
    }*/
}
