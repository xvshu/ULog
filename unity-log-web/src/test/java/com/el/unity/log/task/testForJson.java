package com.el.unity.log.task;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/10.
 */
public class testForJson {

    public static final void main(String[] args){
        testEntity mtest = new testEntity();
        List<testEntity> ltest = new ArrayList<testEntity>();
        mtest.setIp("192.168.2.1");
        mtest.setDomainName("el.buy.com");
        ltest.add(mtest);
        testEntity mtest2 = new testEntity();
        mtest2.setIp("192.168.2.2");
        mtest2.setDomainName("el.pay.com");
        ltest.add(mtest2);
        System.out.println(JSON.toJSON(ltest));

        System.out.println("------------------------------------------------------------------------");

        String tJSON = "[{\"domain\":\"el.2.com\",\"ip\":\"192.168.24.7\"},{\"domain\":\"el.2.com\",\"ip\":\"192.168.24.7\"}]";
        ltest=null;
        ltest=(List)JSON.parseArray(tJSON, testEntity.class);

        System.out.println(ltest.toString());
        testEntity mtest3 = ltest.get(0);
        System.out.println(mtest3.toString());


    }
}
