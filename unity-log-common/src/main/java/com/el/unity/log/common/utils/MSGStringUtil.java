package com.el.unity.log.common.utils;

/**
 * Created by Administrator on 2015/10/29.
 */
public class MSGStringUtil {
    public static String objectIsRepeat(String repaetFiles){
        return "您好，您输入的"+repaetFiles+"已经存在，请您修改后再提交。";
    }
    public static String objectForNewIsNull(){
        return "提交有误，对象为空。";
    }

    public static String inserSuccess(String objectName){
        return "新增"+objectName+"成功。";
    }

    public static String inserBad(String objectName){
        return "新增"+objectName+"失败。";
    }

    public static String updateSuccess(String objectName){
        return "更新"+objectName+"成功。";
    }

    public static String updateBad(String objectName){
        return "更新"+objectName+"成功。";
    }

    public static String deleteSuccess(String objectName){
        return "删除"+objectName+"成功。";
    }

    public static String searchIsNull(String objectName){
        return "查询"+objectName+"结果为空。";
    }

    public static String doSuccess(){
        return "true";
    }

    public static String doBad(){
        return "true";
    }

    public static int hasRepeat = -1;
}
