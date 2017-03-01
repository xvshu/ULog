package com.el.unity.log.web.img;

import com.alibaba.fastjson.JSON;
import com.el.common.result.Result;
import com.el.unity.log.common.es.ESSerach;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.service.es.IESService;
import com.el.unity.log.service.ip.IUnityLogIpService;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import com.el.unity.log.service.type.IUnityLogTypeService;
import com.el.unity.log.web.common.BaseAction;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class ESIMGAction extends BaseAction {

    private Map<String,String> hp;//存储搜索字段与值的对应关系
    private ESSerach esSerach;//搜索用实体
    private String rows;//每页显示的记录数
    private String page;//当前第几页
    private String domainName;//当前菜单关联的域名
    private Integer showMoreSize = 50;

    private String defaultType="";

    @Resource
    private IESService esService;
    @Resource
    private IUnityLogMenuService unityLogMenuService;
    @Resource
    private IUnityLogIpService unityLogIpService;
    @Resource
    private IUnityLogTypeService unityLogTypeService;


    /**
     * 默认返回list显示页
     * @return
     */
    public String list() {
        log.info("=list=>");

        //获取此菜单的id，和取出域名，并通过域名确定ip和type
        String menId = request.getParameter("menId");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }

        if(esSerach==null){
            esSerach=new ESSerach();
        }

        esSerach.setIndex(esindex);
        esSerach.setType(estype);
        UnityLogMenu thisMenu = getMenuByID(Long.valueOf(menId));
        if(null==thisMenu){
            return SUCCESS;
        }
        if(null==hp ){
            hp=new HashMap<>();
        }
        String domainName = thisMenu.getDomainName();//域名
        hp.put("domain",domainName);
        if(null== domainName || domainName.trim().length()==0){
            return SUCCESS;
        }

        Result result = new Result(true);
        result.addDefaultModel("hp",hp);
        result.addDefaultModel("esSerach",esSerach);

        toVm(result);
        log.info("<=list=>");
        return "list";
    }

    /**
     * 根据菜单id获取菜单实体
     * @param MID
     * @return
     */
    private UnityLogMenu getMenuByID(Long MID){
        log.info("=getMenuByID=>");
        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.setPageSize(30);
        unityLogMenuExample.or().andMenIdEqualTo(MID);
        List<UnityLogMenu> listMenu = unityLogMenuService.selectByExample(unityLogMenuExample);
        log.info("<=getMenuByID=>");
        if(null!=listMenu && listMenu.size()>0){
            return listMenu.get(0);
        }else{
            return null;
        }
    }

    public String eschar(){
        String  domain = request.getParameter("domain");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String logType = request.getParameter("logType");
        String className = request.getParameter("className");
        String methodName = request.getParameter("methodName");
        String created_begain = request.getParameter("created_begain");
        String created_end = request.getParameter("created_end");
        String treeId = request.getParameter("treeId");

        if(esSerach==null){
            esSerach=new ESSerach();
        }

        esSerach.setIndex(esindex);
        esSerach.setType(estype);

        if(null==hp ){
            hp=new HashMap<>();
        }
        hp.put("domain",domain);
        hp.put("className",className);
        hp.put("methodName",methodName);
        hp.put("created_begain",created_begain);
        hp.put("created_end",created_end);
        hp.put("treeId",treeId);
        hp.put("logType",logType);


        Result result = new Result(true);
        result.addDefaultModel("hp",hp);
        result.addDefaultModel("esSerach",esSerach);

        return SUCCESS;
    }

    /**
     * 根据条件分页查询
     * @return
     * @throws Exception
     */
    public void getEsDate(){
        log.info("==getMoreInfo>");
        String  domain = request.getParameter("domain");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String logType =  request.getParameter("logType");
        String className = request.getParameter("className");
        String methodName = request.getParameter("methodName");
        String created_begain = request.getParameter("created_begain");
        String created_end = request.getParameter("created_end");
        String treeId = request.getParameter("treeId");


        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }

        String result="";
        //设置查询条件
        Map<String,String> searchWords = new HashMap<String,String>();
        searchWords.put("domain",domain);
        searchWords.put("SearchType","or");
        searchWords.put("HightLight","no");
        searchWords.put("created_begain",created_begain);
        searchWords.put("created_end",created_end);
        searchWords.put("logType",logType);


        ESSerach esSerachs = new ESSerach();
        esSerachs.setSerachWord(searchWords);
        esSerachs.setIndex(esindex);
        esSerachs.setType(estype);
        esSerachs.setOrderFile("created");
        List<List> listReturn = getOneTreeDate(treeId,searchWords,esSerachs);
        writeJson(listReturn);
    }

    public List<List> getOneTreeDate(String treeId , Map<String,String> searchWords,ESSerach esSerachs){
        List<List> listOneTreeDates = new ArrayList<List>();

        searchWords.put("treeId",treeId);
        esSerachs.setSerachWord(searchWords);
        String oneTree = esService.SearchStringByFile(esSerachs);

        List<Map> listOneTreeClass =getEsListFromEsDate(oneTree);
        searchWords.remove("treeId");
        Set<String> classMethods = new HashSet<String>();
         for(Map oneClass : listOneTreeClass){
            String className = (String)oneClass.get("className");
            String methodName = (String)oneClass.get("methodName");
             if(classMethods.contains(className+methodName)){
                 continue;
             }else{
                 classMethods.add(className+methodName);
             }
            searchWords.put("className",className);
            searchWords.put("methodName",methodName);
            esSerachs.setSerachWord(searchWords);
            String oneClassStr = esService.SearchStringByFile(esSerachs);
            listOneTreeDates.add(getEsListFromEsDate(oneClassStr));
        }

        return listOneTreeDates;
    }

    private List<Map> getEsListFromEsDate(String esDateStr){
        List<Map> listReturn = new ArrayList<Map>();
        if(esDateStr==null || esDateStr.isEmpty()){
            return listReturn;
        }
        HashMap oneTreeObject = JSON.parseObject(esDateStr, HashMap.class);
        List<Map> listOneTree =  ((List<Map>)((Map)oneTreeObject.get("hits")).get("hits"));
        for(Map oneEsDate : listOneTree){
            Map oneClass =  (Map)((Map)oneEsDate.get("_source")).get("@message");
            listReturn.add(oneClass);
        }
        return listReturn;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ESSerach getEsSerach() {
        return esSerach;
    }

    public void setEsSerach(ESSerach esSerach) {
        this.esSerach = esSerach;
    }

    public Map<String, String> getHp() {
        return hp;
    }

    public void setHp(Map<String, String> hp) {
        this.hp = hp;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public IUnityLogMenuService getUnityLogMenuService() {
        return unityLogMenuService;
    }

    public void setUnityLogMenuService(IUnityLogMenuService unityLogMenuService) {
        this.unityLogMenuService = unityLogMenuService;
    }

    public IESService getEsService() {
        return esService;
    }

    public void setEsService(IESService esService) {
        this.esService = esService;
    }

    public IUnityLogIpService getUnityLogIpService() {
        return unityLogIpService;
    }

    public void setUnityLogIpService(IUnityLogIpService unityLogIpService) {
        this.unityLogIpService = unityLogIpService;
    }

    public IUnityLogTypeService getUnityLogTypeService() {
        return unityLogTypeService;
    }

    public void setUnityLogTypeService(IUnityLogTypeService unityLogTypeService) {
        this.unityLogTypeService = unityLogTypeService;
    }
}
