package com.el.unity.log.service;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by xvshu on 2016/5/9.
 */
public class TestUrl {
    private String getIndexNameFromString(String url){
        String indexName="";
        if(url.indexOf("esindex")>-1){
            String[] arrSplit=null;
            arrSplit=url.split("\\?");
            if(arrSplit!=null && arrSplit.length>1){
                String urlParm = arrSplit[1];
                String[] parmSplit = urlParm.split("&");
                if(parmSplit!=null && parmSplit.length>0){
                    for(String onekey : parmSplit){
                        if(onekey.indexOf("esindex")>-1){
                            String[] esindex = onekey.split("=");
                            indexName=esindex[1];
                        }
                    }
                }
            }

        }
        return indexName;
    }

    public static Pattern createRegex(List<String> packages) {
        if (CollectionUtils.isEmpty(packages)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String str : packages) {
            sb.append("(^").append(str).append(")*");
        }
        sb.append("([.][a-zA-Z]+)*");
        System.out.println("Pattern is : "+sb.toString());
        return Pattern.compile(sb.toString());
    }

    public static boolean regexMatch(String pkName, Pattern checkRegex) {
        if (checkRegex == null) {//如果没有设置包含的话，则默认为所有的
            return false;
        }
        return checkRegex.matcher(pkName).matches();
    }





}
