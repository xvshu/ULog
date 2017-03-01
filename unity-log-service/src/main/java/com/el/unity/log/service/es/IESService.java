package com.el.unity.log.service.es;

import com.el.common.result.Result;
import com.el.unity.log.common.es.ESSerach;


/**
 * ES搜索操作类
 * Created by Administrator on 2015/9/22.
 */
public interface IESService {

    /**
     * 根据关键字搜索搜索
     * @param esSerach ES搜索实体
     * @return 搜索结果
     */
    public Result SearchByFile(ESSerach esSerach);

    /**
     * 删除某些数据
     * @return 搜索结果
     */
    public Boolean DeleteByQuery();


    /**
     * 根据关键字搜索搜索
     * @param esSerach ES搜索实体
     * @return 搜索结果
     */
    public String SearchStringByFile(ESSerach esSerach);
}
