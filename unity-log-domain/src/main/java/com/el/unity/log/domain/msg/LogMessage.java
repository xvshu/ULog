package com.el.unity.log.domain.msg;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/9/18.
 */
public class LogMessage implements Serializable{
    private String msgId;
    private String localHostIp;
    private String localHostName;
    private String domain;
    private String contextType;
    private String context;
    private String level;
    private Timestamp created;
    private Timestamp indexCreated;
    private String remark;

    public String getLocalHostName() {
        return localHostName;
    }

    public void setLocalHostName(String localHostName) {
        this.localHostName = localHostName;
    }

    public Timestamp getIndexCreated() {
        return indexCreated;
    }

    public void setIndexCreated(Timestamp indexCreated) {
        this.indexCreated = indexCreated;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getLocalHostIp() {
        return localHostIp;
    }

    public void setLocalHostIp(String localHostIp) {
        this.localHostIp = localHostIp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "msgId='" + msgId + '\'' +
                ", localHostIp='" + localHostIp + '\'' +
                ", localHostName='" + localHostName + '\'' +
                ", domain='" + domain + '\'' +
                ", contextType='" + contextType + '\'' +
                ", context='" + context + '\'' +
                ", level='" + level + '\'' +
                ", created=" + created +
                ", indexCreated=" + indexCreated +
                ", remark='" + remark + '\'' +
                '}';
    }

}
