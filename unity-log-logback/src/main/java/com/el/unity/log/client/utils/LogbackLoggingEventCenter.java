package com.el.unity.log.client.utils;


import ch.qos.logback.classic.spi.LoggingEvent;
import com.el.unity.log.client.switcher.LogSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * o(>﹏<)o不要啊
 *
 * @User : Hapic
 * @Date : 2016/3/21 15:48
 */
public class LogbackLoggingEventCenter {
    static ConcurrentLinkedQueue<LoggingEvent> loggingEventQueue=new ConcurrentLinkedQueue<LoggingEvent>();
    static AtomicInteger loggingEventQueueSize= new AtomicInteger(0);
//    public static Integer defaultListCount=10;

    public static void add(LoggingEvent loggingEvent){
        //加操作
        loggingEventQueue.add(loggingEvent);

        loggingEventQueueSize.incrementAndGet();
    }

    public static Integer getDefaultListCount() {
        return LogSwitcher.getCapacity();
    }

    public static LoggingEvent poll(){
        LoggingEvent poll = loggingEventQueue.poll();

        if(poll==null){
            return null;
        }
        //减去操作
        loggingEventQueueSize.decrementAndGet();
        return poll;
    }

    public static boolean isEmpty(){
        int i = loggingEventQueueSize.intValue();

        return i<=0;
    }

    public static int size(){
        return loggingEventQueueSize.intValue();
    }


    public static List<LoggingEvent> list(){
//        Iterator<LoggingEvent> iterator = loggingEventQueue.iterator();
        int a=0;
        List<LoggingEvent> list= new ArrayList<LoggingEvent>();

        while (!isEmpty() && a<getDefaultListCount()){
            LoggingEvent loggingEvent = poll();
            list.add(loggingEvent);
            a++;
        }

        return list;
    }

}
