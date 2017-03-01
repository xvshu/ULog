package com.el.unity.log.client.msg;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/9/22.
 */
public class LogMessage implements Serializable {
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

    private String treeId;
    private String requestId;
    private String transactionId;
    private String className;
    private String methodName;
    private Long startTime;
    private Long endTime;
    private Long spendTime;
    private String exceptionMsg;
    private String percentage;
    private Long methodStart;
    private Long methodDnd;
    private Long totalTime;
    private String isBeginMethod;

    private Boolean hasException=false;
    private String exceptionType;


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


    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Long getMethodStart() {
        return methodStart;
    }

    public void setMethodStart(Long methodStart) {
        this.methodStart = methodStart;
    }

    public Long getMethodDnd() {
        return methodDnd;
    }

    public void setMethodDnd(Long methodDnd) {
        this.methodDnd = methodDnd;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getIsBeginMethod() {
        return isBeginMethod;
    }

    public void setIsBeginMethod(String isBeginMethod) {
        this.isBeginMethod = isBeginMethod;
    }

    public Boolean getHasException() {
        return hasException;
    }

    public void setHasException(Boolean hasException) {
        this.hasException = hasException;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
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
