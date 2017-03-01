package com.el.unity.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Administrator on 2015/9/8.
 */
public class WriteLog {
    protected static final Log logger = LogFactory.getLog(WriteLog.class);

//flume-ng agent -c conf -f ../conf/log4j.conf --name a1 -Dflume.root.logger=INFO,console
    public static void main(String[] args) throws InterruptedException {
        DOMConfigurator.configure(WriteLog.class.getClassLoader().getResource("").getFile() + "log4j.xml");
        int i=0;
        while (true) {
            // 每隔两秒log输出一下当前系统时间戳
            //logger.info("=WriteLog=>"+new Date().getTime());
            Thread.sleep(1000);
            try {
                throw new Exception("t_"+(i++));
            }
            catch (Exception e) {
                logger.error("error:" + e.getMessage());
            }
        }
    }
}
