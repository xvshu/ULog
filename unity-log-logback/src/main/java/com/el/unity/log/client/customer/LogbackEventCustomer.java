package com.el.unity.log.client.customer;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.el.unity.log.client.LogBackFlumeAppender;
import com.el.unity.log.client.utils.LogbackLoggingEventCenter;


import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 *
 * @User : Hapic
 * @Date : 2016/3/25 16:41
 */
public class LogbackEventCustomer implements Runnable {

    private LogBackFlumeAppender logBackFlumeAppender;

    public LogbackEventCustomer(LogBackFlumeAppender logBackFlumeAppender) {
        this.logBackFlumeAppender = logBackFlumeAppender;
    }

    @Override
    public void run() {

        try {
            while (true){

                while (!LogbackLoggingEventCenter.isEmpty()){
                    List<LoggingEvent> list = LogbackLoggingEventCenter.list();
                    logBackFlumeAppender.doThread(list);
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
