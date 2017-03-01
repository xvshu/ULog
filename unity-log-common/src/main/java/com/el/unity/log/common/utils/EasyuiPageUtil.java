package com.el.unity.log.common.utils;

import java.util.List;

/**
 * 封装符合easyui格式的分页对象
 * Created by Administrator on 2015/9/24.
 */
public class EasyuiPageUtil {
    private int total; //共有多少条
    private List rows; //显示实体集合

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
