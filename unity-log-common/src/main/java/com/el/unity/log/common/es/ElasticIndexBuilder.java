package com.el.unity.log.common.es;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by xvshu on 2016/8/19.
 */
public class ElasticIndexBuilder {

    public static final String DEFAULT_DATE_FORMAT = "dd";
    public static final String DEFAULT_TIME_ZONE = "Etc/UTC";
    public static final int minNum = 1;
    public static final int maxNum = 31;
    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("dd",
            TimeZone.getTimeZone("Etc/UTC"));

    public static void configure(String dateFormatString,String timeZoneString){
        if(dateFormatString==null || dateFormatString.trim().isEmpty()){
            dateFormatString=DEFAULT_DATE_FORMAT;
        }
        if(timeZoneString==null || timeZoneString.trim().isEmpty()){
            timeZoneString=DEFAULT_TIME_ZONE;
        }
        fastDateFormat=FastDateFormat.getInstance(dateFormatString,
                TimeZone.getTimeZone(timeZoneString));
    }

    private static String[] buildIndexNames(String indexNameBase,Long startDate,Long endDate){
        ArrayList<String> listForReturn = new ArrayList<String>();
        if(indexNameBase == null || indexNameBase.trim().isEmpty()){
            return null;
        }
        if(startDate == null || endDate==null || startDate==0 || endDate==0){
            long timestamp =System.currentTimeMillis();
            startDate=timestamp;
            endDate=timestamp;
        }

        int startNum =  Integer.valueOf(fastDateFormat.format(startDate));
        int endNum =  Integer.valueOf(fastDateFormat.format(endDate));

        List<String> listForAppend = new ArrayList<String>();
        if(endDate - startDate > 25*24*60*60*1000l ){
            for(int i=minNum;i<=maxNum;i++){
                listForAppend.add(changeIndexNumbers(i));
            }
        }else if(startNum<=endNum){
            for(int i=startNum;i<=endNum;i++){
                listForAppend.add(changeIndexNumbers(i));
            }
        }else{
            for(int i = minNum ; i <=endNum;i++){
                listForAppend.add(changeIndexNumbers(i));
            }
            for (int i = startNum; i <=maxNum ; i++) {
                listForAppend.add(changeIndexNumbers(i));
            }
        }

        String[] indexnames = indexNameBase.split("\\,");
        for(String oneIndexName : indexnames){
            listForReturn.add(oneIndexName);
            for(String oneAppend :listForAppend ){
                listForReturn.add(oneIndexName+"-"+oneAppend);
            }
        }
        return listForReturn.toArray(new String[listForReturn.size()]);
    }

    public static String changeIndexNumbers(int i){
        if(i<10){
            return "0"+String.valueOf(i);
        }else{
            return String.valueOf(i);
        }
    }

    public static String[] deleteNoHavaIndexs( String[] allIndexS){
        ArrayList<String> listForReturn = new ArrayList<String>();
        if(ESUtils.IndexSet == null || ESUtils.IndexSet.isEmpty()){
            ESUtils.reflashIndexNames();
        }
        if(ESUtils.IndexSet != null &&  !ESUtils.IndexSet.isEmpty()){
            for(String oneIndex : allIndexS){
                if(ESUtils.IndexSet.contains(oneIndex)){
                    listForReturn.add(oneIndex);
                }
            }
        }
        return listForReturn.toArray(new String[listForReturn.size()]);
    }

    public static String[] getIndexNames(String indexNameBase,Long startDate,Long endDate){
        String[] allIndexs =  buildIndexNames(indexNameBase,startDate,endDate);
        allIndexs = deleteNoHavaIndexs(allIndexs);
        return allIndexs;
    }

    public static void main(String[] args) {
        String[] indexs = ElasticIndexBuilder.getIndexNames("xvshu-A,xvshu-B",null,null);
        System.out.println( JSON.toJSONString(indexs));

    }

}
