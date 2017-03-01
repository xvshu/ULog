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
    private static String log4jSync="true";//log4j默认是否启动采集

    @DisconfFileItem(name = "log4jSync", associateField = "log4jSync")
    public static String getLog4jSync() {
        return log4jSync;
    }

    public static void setLog4jSync(String log4jSync) {
        LogSwitcher.log4jSync = log4jSync;
    }

}
