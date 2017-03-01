package com.el.unity.log.client.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xvshu on 2016/5/18.
 */
public class IDUtils {
    private static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
    private static AtomicLong index = new AtomicLong(System.currentTimeMillis() / 1000 - 1420041600);

    public static String getID() throws Exception {
        String ip = HostUtils.getLocalHostIp();
        return String.valueOf( index.incrementAndGet() << 28 | (map.putIfAbsent(ip, ipsToLong(ip.split(",")))==null?ipsToLong(ip.split(",")):map.putIfAbsent(ip, ipsToLong(ip.split(",")))));
    }

    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    private static long ipsToLong(String[] strIp) throws Exception {
        long ipvalue = 0;
        for (String ip : strIp) {
            ipvalue += ipToLong(ip);
        }
        return ipvalue;
    }

    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    private static long ipToLong(String strIp)throws Exception
    {
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3+1));
        return (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

}
