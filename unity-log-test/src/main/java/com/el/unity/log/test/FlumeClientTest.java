package com.el.unity.log.test;

import org.apache.flume.Event;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientConfigurationConstants;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.clients.log4jappender.Log4jAvroHeaders;
import org.apache.flume.event.EventBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2015/12/1.
 */
public class FlumeClientTest {
    public static void send() {
        Map<String, String> hdrs = new HashMap<String, String>();
        Properties props = new Properties();
        props.setProperty(RpcClientConfigurationConstants.CONFIG_HOSTS, "h1");
        props.setProperty(RpcClientConfigurationConstants.CONFIG_HOSTS_PREFIX + "h1",
                "192.168.2.16:12346");
        props.setProperty(RpcClientConfigurationConstants.CONFIG_CONNECT_TIMEOUT,
                String.valueOf(20000));
        props.setProperty(RpcClientConfigurationConstants.CONFIG_REQUEST_TIMEOUT,
                String.valueOf(20000));
/*RpcClientFactory.getDefaultInstance("192.168.2.16", 4444, 10);// */
        try {
            RpcClient rpcClient = RpcClientFactory.getInstance(props);
            int i = 0;
            while (true) {
                if(!rpcClient.isActive()){
                    rpcClient.close();
                    rpcClient = RpcClientFactory.getInstance(props);
                }
                System.out.println("line_"+i++);
                Event flumeEvent = EventBuilder.withBody("test_"+i+"\n", Charset.forName("UTF8"), hdrs);
                hdrs.put(Log4jAvroHeaders.LOGGER_NAME.toString(), "test");
                hdrs.put(Log4jAvroHeaders.TIMESTAMP.toString(),
                        String.valueOf(System.currentTimeMillis()));
                hdrs.put(Log4jAvroHeaders.LOG_LEVEL.toString(), String.valueOf(12346));
                rpcClient.append(flumeEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        send();
    }

}
