package com.el.unity.log.client.monitor;

import com.el.unity.log.client.LogBackFlumeAppender;
import com.el.unity.log.client.switcher.LogSwitcher;
import org.apache.flume.api.RpcClient;

import java.util.concurrent.atomic.AtomicLong;

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
 * @Date : 2016/3/30 17:26
 */
public class MonitorRunnable implements Runnable {

    private LogBackFlumeAppender logBackFlumeAppender;


    public MonitorRunnable(LogBackFlumeAppender logBackFlumeAppender) {
        this.logBackFlumeAppender=logBackFlumeAppender;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep(20 * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if("true".equals(LogSwitcher.getLogbackSync())) {
                        RpcClient rpcClient = logBackFlumeAppender.getRpcClient();

                        if (rpcClient == null || !rpcClient.isActive()) {
                            System.out.println("ulog monitor connect...");
                            logBackFlumeAppender.reconnect();
                        }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


}
