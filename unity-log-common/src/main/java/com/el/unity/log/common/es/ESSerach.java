package com.el.unity.log.common.es;


import java.util.Map;

/**
 * 封装ES搜索实体
 * Created by Administrator on 2015/9/23.
 */
public class ESSerach {
    private String index; //索引
    private String type; //类型
    private int from ; //分页：当前页码
    private int size ; //分页：每页条数
    private Map<String,String> serachWord; //搜索集合（key为字段名，value为关键字）
    private String orderFile; //排序字段

    private Boolean hasAggregation=false;


    /*----------------get/set----------------*/

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, String> getSerachWord() {
        return serachWord;
    }

    public void setSerachWord(Map<String, String> serachWord) {
        this.serachWord = serachWord;
    }


    public String getOrderFile() {
        return orderFile;
    }

    public void setOrderFile(String orderFile) {
        this.orderFile = orderFile;
    }

    public Boolean getHasAggregation() {
        return hasAggregation;
    }

    public void setHasAggregation(Boolean hasAggregation) {
        this.hasAggregation = hasAggregation;
    }

    @Override
    public String toString() {
        return "esSerach{" +
                "index=" + index +
                "type=" + type +
                "from=" + from +
                "size=" + size +
                "serachWord=" + serachWord +
                '}';
    }
}
