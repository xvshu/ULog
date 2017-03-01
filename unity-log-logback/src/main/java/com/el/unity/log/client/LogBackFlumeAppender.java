package com.el.unity.log.client;


import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import com.alibaba.fastjson.JSON;
import com.el.unity.log.client.customer.LogbackEventCustomer;
import com.el.unity.log.client.monitor.MonitorRunnable;
import com.el.unity.log.client.msg.LogMessage;
import com.el.unity.log.client.switcher.LogSwitcher;
import com.el.unity.log.client.utils.HostUtils;
import com.el.unity.log.client.utils.LogbackLoggingEventCenter;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.FlumeException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientConfigurationConstants;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.clients.log4jappender.Log4jAvroHeaders;
import org.apache.flume.event.EventBuilder;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LogBackFlumeAppender extends ContextAwareBase implements Appender<LoggingEvent> {
    private String hostname;
    private int port;
    private boolean unsafeMode = true;
    private long timeout = RpcClientConfigurationConstants.DEFAULT_REQUEST_TIMEOUT_MILLIS * 2;
    private boolean avroReflectionEnabled;
    private String avroSchemaUrl;
    private String domain;
    volatile RpcClient rpcClient = null;
    private boolean debugLog;
    private Layout<LoggingEvent> layout;

    volatile MonitorRunnable monitorRunnable=new MonitorRunnable(this);

    ExecutorService excutor = Executors.newSingleThreadExecutor();

    ExecutorService excutorCustomer = Executors.newSingleThreadExecutor();


    public LogBackFlumeAppender() {
        excutor.submit(monitorRunnable);
        excutorCustomer.submit(new LogbackEventCustomer(this));
	  }


    public LogMessage createLogMsg(LoggingEvent event) {
        LogMessage msg = new LogMessage();
        msg.setLocalHostIp(HostUtils.getLocalHostIp());
        msg.setLocalHostName(HostUtils.getLocalHostName());
        msg.setMsgId(UUID.randomUUID().toString());
        msg.setDomain(domain);
        msg.setCreated(new Timestamp(event.getTimeStamp()));
        msg.setContext(getLayout().doLayout(event));
        msg.setLevel(event.getLevel().toString());
        if (null != event.getLoggerRemoteView()) {
            if (event.getLoggerRemoteView().getName().contains(".")) {
                msg.setContextType(event.getLoggerRemoteView().getName().substring(event.getLoggerRemoteView().getName().lastIndexOf(".") + 1));
            } else {
                msg.setContextType(event.getLoggerRemoteView().getName());
            }
        }
        return msg;
    }

    protected void append(final LoggingEvent eventObject) {

        try {
            if("true".equals(LogSwitcher.getLogbackSync())) {//开启采集日志
                LogbackLoggingEventCenter.add(eventObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void start() {
        started = true;
        System.setProperty("log4j.debug", String.valueOf(debugLog));
        activateOptions();
    }


    public boolean reconnect() throws FlumeException {
        close();
        return activateOptions();
    }

    public boolean activateOptions() throws FlumeException {
        Properties props = new Properties();
        props.setProperty(RpcClientConfigurationConstants.CONFIG_HOSTS, "h1");
        props.setProperty(RpcClientConfigurationConstants.CONFIG_HOSTS_PREFIX + "h1",
                hostname + ":" + port);
        props.setProperty(RpcClientConfigurationConstants.CONFIG_CONNECT_TIMEOUT,
                String.valueOf(timeout));
        props.setProperty(RpcClientConfigurationConstants.CONFIG_REQUEST_TIMEOUT,
                String.valueOf(timeout));
        props.setProperty(RpcClientConfigurationConstants.CONFIG_MAX_BACKOFF, "1000");
        props.setProperty(RpcClientConfigurationConstants.CONFIG_BACKOFF, "false");
        props.setProperty("Blocking", "false");

        try {
            rpcClient = RpcClientFactory.getInstance(props);
            if (layout != null) {
                layout.start();
            }
            return true;
        } catch (Exception e) {
            LogLog.error("RPC client creation failed! " + e.getMessage());
            if (unsafeMode) {
                return false;
            }
            throw e;
        }
    }

    public void close() throws FlumeException {
        // Any append calls after this will result in an Exception.
        if (rpcClient != null) {
            try {
                rpcClient.close();
            } catch (Throwable ex) {
                ex.printStackTrace();
                LogLog.error("Error while trying to close RpcClient.", ex);
                if (unsafeMode) {
                    return;
                }
                throw ex;
            } finally {
                rpcClient = null;
            }
        } else {
            String errorMsg = "Flume log4jappender already closed!";
            LogLog.error(errorMsg);
            if (unsafeMode) {
                return;
            }
            throw new FlumeException(errorMsg);
        }
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isUnsafeMode() {
        return unsafeMode;
    }

    public void setUnsafeMode(boolean unsafeMode) {
        this.unsafeMode = unsafeMode;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isAvroReflectionEnabled() {
        return avroReflectionEnabled;
    }

    public void setAvroReflectionEnabled(boolean avroReflectionEnabled) {
        this.avroReflectionEnabled = avroReflectionEnabled;
    }

    public String getAvroSchemaUrl() {
        return avroSchemaUrl;
    }

    public void setAvroSchemaUrl(String avroSchemaUrl) {
        this.avroSchemaUrl = avroSchemaUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public RpcClient getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public boolean isDebugLog() {
        return debugLog;
    }

    public void setDebugLog(boolean debugLog) {
        this.debugLog = debugLog;
    }

    protected boolean started = false;

    /**
     * The guard prevents an appender from repeatedly calling its own doAppend
     * method.
     */
    private volatile boolean guard = false;

    /**
     * Appenders are named.
     */
    protected String name;

    private FilterAttachableImpl<LoggingEvent> fai = new FilterAttachableImpl<LoggingEvent>();

    public String getName() {
        return name;
    }

    private int statusRepeatCount = 0;

    private int exceptionCount = 0;

    static final int ALLOWED_REPEATS = 5;

    public void doAppend(LoggingEvent eventObject) {

        // WARNING: The guard check MUST be the first statement in the
        // doAppend() method.
        if (guard) {
            return;
        }
        try {
            guard = true;
            if (!this.started) {
                if (statusRepeatCount++ < ALLOWED_REPEATS) {
                    addStatus(new WarnStatus(
                            "Attempted to append to non started appender [" + name + "].",
                            this));
                }
                return;
            }
            if (getFilterChainDecision(eventObject) == FilterReply.DENY) {
                return;
            }
            this.append(eventObject);
        } catch (Throwable e) {
            e.printStackTrace();
            if (exceptionCount++ < ALLOWED_REPEATS) {
                try {
                    addError("Appender [" + name + "] failed to append.", e);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            guard = false;
        }
    }

    public void doThread(List<LoggingEvent> loggingEventList){

        if (rpcClient == null) {
            String errorMsg = "Cannot Append to Appender! Appender either closed or not setup correctly!";
            LogLog.error(errorMsg);
            if (unsafeMode) {
                return;
            }
            throw new FlumeException(errorMsg);
        }
        if (null == domain || "".equals(domain)) {
            if (unsafeMode) {
                return;
            }
            throw new FlumeException("domain is null");
        }

        if (!rpcClient.isActive()) {
            reconnect();
        }


        List<Event> eventList= new ArrayList<>();

        for(LoggingEvent eventObject:loggingEventList){

            //Client created first time append is called.
            Map<String, String> hdrs = new HashMap<String, String>();
            hdrs.put(Log4jAvroHeaders.LOGGER_NAME.toString(), eventObject.getLoggerRemoteView().getName());
            hdrs.put(Log4jAvroHeaders.TIMESTAMP.toString(),
                    String.valueOf(eventObject.getTimeStamp()));
            hdrs.put(Log4jAvroHeaders.LOG_LEVEL.toString(),
                    String.valueOf(eventObject.getLevel().levelInt));
            hdrs.put(Log4jAvroHeaders.MESSAGE_ENCODING.toString(), "UTF8");
            LogMessage logMsg = createLogMsg(eventObject);

            Event flumeEvent = EventBuilder.withBody(JSON.toJSONString(logMsg), Charset.forName("UTF8"), hdrs);
            LogLog.debug("Log-back-Msg:" + logMsg.toString());
            LogLog.debug("Log-back-Msg-event:" + JSON.toJSONString(logMsg));

            eventList.add(flumeEvent);
        }
        try {
            if (null != rpcClient) {
                rpcClient.appendBatch(eventList);
            }
        } catch (EventDeliveryException e) {
            e.printStackTrace();
            String msg = "Log-back-Msg Flume append() failed.";
            com.el.unity.log.client.LogLog.error(msg);
            if (unsafeMode) {
                return;
            }
            throw new FlumeException(msg + " Log-back-Msg Exception follows.", e);
        } catch (Error ex) {
            ex.printStackTrace();
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void stop() {
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public String toString() {
        return this.getClass().getName() + "[" + name + "]";
    }

    public void addFilter(Filter<LoggingEvent> newFilter) {
        fai.addFilter(newFilter);
    }

    public Filter getFirstFilter() {
        return fai.getFirstFilter();
    }

    public void clearAllFilters() {
        fai.clearAllFilters();
    }

    public FilterReply getFilterChainDecision(LoggingEvent event) {
        return fai.getFilterChainDecision(event);
    }

    public Layout<LoggingEvent> getLayout() {
        return layout;
    }

    public void setLayout(Layout<LoggingEvent> layout) {
        this.layout = layout;
    }
}
