package com.el.unity.log.task;

/**
 * Created by Administrator on 2015/10/10.
 */
public class testEntity {
    private String ip;
    private String domainName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return "ip:"+ip
                +"domainName"+domainName;
    }
}
