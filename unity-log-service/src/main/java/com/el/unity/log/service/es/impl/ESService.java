package com.el.unity.log.service.es.impl;

import com.el.common.result.Result;
import com.el.unity.log.common.es.ESSerach;
import com.el.unity.log.common.es.ESUtils;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.service.es.IESService;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ES搜索操作类
 * Created by Administrator on 2015/9/22.
 */
@Service("esService")
public class ESService implements IESService {
    protected final Logger log = LogManager.getLogger(ESService.class.getName());

    @Resource
    private ESUtils esUtils;

    @Resource
    private IUnityLogMenuService unityLogMenuService;

    private long esTimeOut=1000*3600 * 24 *7;


    /**
     * 根据关键字搜索搜索
     * @param esSerach ES搜索实体
     * @return 搜索结果
     */
    @Override
    public Result SearchByFile(ESSerach esSerach) {
        long starttime = System.currentTimeMillis();
        log.info("=SearchByFile=> MDC:" + MDC.get("tnid"));

        Result result = new Result(true);
        String  AllObject = "";
        AllObject= esUtils.getData(esSerach);
        result.addDefaultModel("msg",AllObject);
        result.addDefaultModel("esSerach",esSerach);
        log.info("<=SearchByFile=>");
        System.out.println("=======>ESService.SearchByFile=======>spen time:"+(System.currentTimeMillis()-starttime));
        return result;
    }

    @Override
    public Boolean DeleteByQuery() {
        List<String> listAllIndex = getAllIndexs();
        listAllIndex.add("logstash");
        for(String oneIndexName : listAllIndex){
            if(oneIndexName!=null && !oneIndexName.trim().isEmpty()){
                deleteOneIndex(oneIndexName);
            }
        }
        return true;
    }

    private Boolean deleteOneIndex(String indexName){
        ESSerach esSerach = new ESSerach();
        //设置查询条件
        Map<String,String> searchWords = new HashMap<String,String>();
        esSerach.setIndex(indexName);
        Long nowtime = System.currentTimeMillis();
        nowtime = nowtime - esTimeOut;
        String  created_begain_more_info  = nowtime.toString();

        searchWords.put("created_begain_more_info",created_begain_more_info);
        searchWords.put("created_begain_filter","lte");
        searchWords.put("SearchType","and");
        esSerach.setSerachWord(searchWords);
        esUtils.deleteData(esSerach);
        return true;
    }

    private List<String> getAllIndexs(){
        List<String> listAllIndex = new ArrayList<String>();

        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.or().andParentMenIdIsNotNull();
        //获取所有的二级菜单
        List<UnityLogMenu> listAllOneLevelMenu = unityLogMenuService.selectByExample(unityLogMenuExample);
        if(listAllOneLevelMenu!=null && !listAllOneLevelMenu.isEmpty()){
            for(UnityLogMenu oneMenu:listAllOneLevelMenu){

                String oneIndexName = getIndexNameFromString(oneMenu.getMenUrl());
                if(oneIndexName!=null && !oneIndexName.trim().isEmpty() && !listAllIndex.contains(oneIndexName)){
                    listAllIndex.add(oneIndexName);
                }

                String oneExtendUrlIndexName = getIndexNameFromString(oneMenu.getExtendUrl());
                if(oneExtendUrlIndexName!=null && !oneExtendUrlIndexName.trim().isEmpty()  && !listAllIndex.contains(oneExtendUrlIndexName)){
                    listAllIndex.add(oneExtendUrlIndexName);
                }

                String oneEsAppenderUrlIndexName = getIndexNameFromString(oneMenu.getEsAppenderUrl());
                if(oneEsAppenderUrlIndexName!=null && !oneEsAppenderUrlIndexName.trim().isEmpty() && !listAllIndex.contains(oneEsAppenderUrlIndexName)){
                    listAllIndex.add(oneEsAppenderUrlIndexName);
                }

                String oneEsFileUrlIndexName = getIndexNameFromString(oneMenu.getEsFileUrl());
                if(oneEsFileUrlIndexName!=null && !oneEsFileUrlIndexName.trim().isEmpty() && !listAllIndex.contains(oneEsFileUrlIndexName)){
                    listAllIndex.add(oneEsFileUrlIndexName);
                }

                String oneImgPerformsUrlIndexName = getIndexNameFromString(oneMenu.getImgPerformsUrl());
                if(oneImgPerformsUrlIndexName!=null && !oneImgPerformsUrlIndexName.trim().isEmpty() && !listAllIndex.contains(oneImgPerformsUrlIndexName)){
                    listAllIndex.add(oneImgPerformsUrlIndexName);
                }

                String oneImgTimeUrlIndexName = getIndexNameFromString(oneMenu.getImgTimeUrl());
                if(oneImgTimeUrlIndexName!=null && !oneImgTimeUrlIndexName.trim().isEmpty() && !listAllIndex.contains(oneImgTimeUrlIndexName)){
                    listAllIndex.add(oneImgTimeUrlIndexName);
                }
            }
        }
        return listAllIndex;

    }

    private String getIndexNameFromString(String url){
        String indexName="";

        if(url!=null && url.indexOf("esindex")>-1){
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

    public String SearchStringByFile(ESSerach esSerach){
        log.info("=SearchByFile=>");
        String  AllObject = "";
        AllObject= esUtils.getData(esSerach);
        return AllObject;
    }

    public ESUtils getEsUtils() {
        return esUtils;
    }

    public void setEsUtils(ESUtils esUtils) {
        this.esUtils = esUtils;
    }
}
