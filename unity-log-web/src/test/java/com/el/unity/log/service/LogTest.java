package com.el.unity.log.service;

import com.alibaba.fastjson.JSON;
import com.el.unity.log.BaseTest;
import com.el.unity.log.client.msg.LogMessage;
import com.el.unity.log.client.utils.HostUtils;
import com.el.unity.log.service.es.IESService;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/9/24.
 */
public class LogTest extends BaseTest {

    private IUnityLogMenuService unityLogMenuService;

    @Resource
    private IESService esService;

    @Test
    public void select(){
        unityLogMenuService.selectByPrimaryKey(1);

    }
    public IUnityLogMenuService getUnityLogMenuService() {
        return unityLogMenuService;
    }

    public void setUnityLogMenuService(IUnityLogMenuService unityLogMenuService) {
        this.unityLogMenuService = unityLogMenuService;
    }

    @Test
    public void testDeleteIndex(){
        esService.DeleteByQuery();
    }

    public static void main(String[] args){
        LogMessage logMessage = new LogMessage();
        Exception ex = new Exception("test");
        logMessage.setHasException(false);
        logMessage.setExceptionType(ex.getClass().getName());
        logMessage.setExceptionMsg(ex.getMessage());
        logMessage.setContext("=test=> is begin");
        logMessage.setCreated(new Timestamp(System.currentTimeMillis()));
        logMessage.setDomain("log4jDomainValues");
        logMessage.setLocalHostIp(HostUtils.getLocalHostIp());
        logMessage.setLocalHostName(HostUtils.getLocalHostName());
        logMessage.setClassName("className");
        logMessage.setMethodName("methodName");

        System.out.println(JSON.toJSONString(logMessage));
    }





}
