package com.el.unity.log.web.es;

import com.el.common.result.Result;
import com.el.unity.log.common.es.ESSerach;
import com.el.unity.log.domain.ip.UnityLogIp;
import com.el.unity.log.domain.ip.UnityLogIpExample;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.domain.type.UnityLogType;
import com.el.unity.log.domain.type.UnityLogTypeExample;
import com.el.unity.log.service.es.IESService;
import com.el.unity.log.service.ip.IUnityLogIpService;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import com.el.unity.log.service.type.IUnityLogTypeService;
import com.el.unity.log.web.common.BaseAction;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class ESFileSourceAction extends BaseAction {

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
    public String index(){
        return SUCCESS;
    }

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
        String logType = request.getParameter("logType");
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
        esSerach.setOrderFile("created");

        UnityLogMenu thisMenu = getMenuByID(Long.valueOf(menId));
        if(null==thisMenu){
            return SUCCESS;
        }
        if(null==hp ){
            hp=new HashMap<>();
        }
        domainName = thisMenu.getDomainName();//域名
        hp.put("domain",domainName);
        hp.put("logType",logType==null||logType.trim().isEmpty()?"":logType);

        if(null== domainName || domainName.trim().length()==0){
            return SUCCESS;
        }

        Result result = new Result(true);
        result.addDefaultModel("hp",hp);
        result.addDefaultModel("esSerach",esSerach);
        result.addDefaultModel("domainName",domainName);
        toVm(result);
        log.info("<=list=>");
        return "list";
    }

    /**
     * 根据条件分页查询
     * @return
     * @throws Exception
     */
    public String serachES() throws Exception{
        log.info("=serachES=>");
        Result result=null;
        //设置查询条件
        rows=rows == null || rows.length() == 0 ? "50" : rows;
        page=page == null || page.length() == 0 ? "1" : page;
        int size = Integer.parseInt(rows);
        int numPage =  Integer.parseInt( page);
        esSerach.setSize(size);
        esSerach.setFrom(size*numPage-size);
        esSerach.setSerachWord(hp);

        //执行查询
        result =esService.SearchByFile(esSerach);

        //回写分页信息
        result.addDefaultModel("rows",rows);
        result.addDefaultModel("page",page);
        result.addDefaultModel("esSerach",esSerach);
        result.addDefaultModel("domainName",domainName);
        toVm(result);
        log.info("<=serachES=>");
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

    /**
     * 根据域名获取绑定好的IP（多个用空格分割）
     * @return
     */
    public void getIpListByDomainName(){
        log.info("=getIpListByDomainName=>");
        domainName = request.getParameter("domainName");
        String listIp="";
        UnityLogIpExample unityLogIpExample = new UnityLogIpExample();
        unityLogIpExample.or().andDomainNameEqualTo(domainName);
        List<UnityLogIp> listAllID = unityLogIpService.selectByExample(unityLogIpExample);
        writeJson(listAllID);
        log.info("<=getIpListByDomainName=>");

    }

    /**
     * 根据域名获取关联的类型（多个类型用 | 符号分割）
     * @return
     */
    public void getTypeListByDomainName(){
        log.info("=getTypeListByDomainName=>");
        domainName = request.getParameter("domainName");
        String listType="";
        UnityLogTypeExample unityLogTypeExample = new UnityLogTypeExample();
        unityLogTypeExample.or().andDomainNameEqualTo(domainName);
        List<UnityLogType> listAllType = unityLogTypeService.selectByExample(unityLogTypeExample);
        writeJson(listAllType);
        log.info("<=getTypeListByDomainName=>");
    }

    public String showmorefilesource(){
        log.info("==getMoreInfo>");
        String  created_begain_more_info  = request.getParameter("created_begain_more_info");
        String  localHostIp = request.getParameter("localHostIp");
        String  domain = request.getParameter("domain");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String treeId = request.getParameter("treeId");
        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }

        String  commandtype = "gte";


        Result result=null;
        //设置查询条件
        Map<String,String> searchWords = new  HashMap<String,String> ();

        searchWords.put("domain",domain);
        searchWords.put("SearchType","or");
        searchWords.put("HightLight","no");
        searchWords.put("treeId",treeId);



        searchWords.put("localHostIp",localHostIp);
        if(created_begain_more_info !=null && !created_begain_more_info.trim().isEmpty()){
            searchWords.put("created_begain_more_info",created_begain_more_info);
            searchWords.put("created_begain_filter", commandtype);
        }


        ESSerach esSerachs = new ESSerach();
        esSerachs.setSerachWord(searchWords);
//        esSerachs.setSize(50);
//        esSerachs.setFrom(0);

        esSerachs.setIndex(esindex);
        esSerachs.setType(estype);
        esSerachs.setOrderFile("created");

        //执行查询
        result =esService.SearchByFile(esSerachs);
        result.addDefaultModel("searchWords",searchWords);
        result.addDefaultModel("esindex",esindex);
        result.addDefaultModel("estype",estype);
        result.addDefaultModel("treeId",treeId);
        toVm(result);

        return SUCCESS;
    }


    public String showExceptionCount(){
        String  domain = request.getParameter("domain");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String created_begain = request.getParameter("created_begain");
        String created_end = request.getParameter("created_end");

        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }


        if(esSerach==null){
            esSerach=new ESSerach();
        }

        if(hp==null){
            hp=new HashMap<String,String>();
        }

        esSerach.setIndex(esindex);
        esSerach.setType(estype);
        esSerach.setOrderFile("created");
        esSerach.setHasAggregation(true);
        hp.put("domain",domain);
        hp.put("created_begain",created_begain);
        hp.put("created_end",created_end);
        hp.put("SearchType","or");
        hp.put("HightLight","no");
        hp.put("hasException","true");
        esSerach.setSerachWord(hp);
        esSerach.setHasAggregation(true);
        Result result=null;
        //执行查询
        result =esService.SearchByFile(esSerach);
        result.addDefaultModel("hp",hp);
        result.addDefaultModel("esSerach",esSerach);
        result.addDefaultModel("domainName",domainName);
        toVm(result);
        return "esmethodchar";
    }

    public String showMethodsCount(){
        String  domain = request.getParameter("domain");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String logType = request.getParameter("logType");
        String created_begain = request.getParameter("created_begain");
        String created_end = request.getParameter("created_end");

        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }


        if(esSerach==null){
            esSerach=new ESSerach();
        }

        if(hp==null){
            hp=new HashMap<String,String>();
        }

        esSerach.setIndex(esindex);
        esSerach.setType(estype);
        esSerach.setOrderFile("created");
        esSerach.setHasAggregation(true);
        hp.put("domain",domain);
        hp.put("created_begain",created_begain);
        hp.put("created_end",created_end);
        hp.put("logType",logType);
        hp.put("SearchType","or");
        hp.put("HightLight","no");
        esSerach.setSerachWord(hp);
        esSerach.setHasAggregation(true);
        Result result=null;
        //执行查询
        result =esService.SearchByFile(esSerach);
        Result resultException=null;
        hp.put("level","ERROR");
        resultException =esService.SearchByFile(esSerach);
        result.addDefaultModel("msgException",resultException.get("msg"));
        toVm(result);
        return "esmethodchar";
    }




    public void showMoreLogInfo(){
        log.info("==getMoreInfo>");
        String  created_begain_more_info  = request.getParameter("created_begain_more_info");
        String  localHostIp = request.getParameter("localHostIp");
        String  domain = request.getParameter("domain");
        String  upcount = request.getParameter("upcount");
        String  downcount = request.getParameter("downcount");
        String  commandtype = request.getParameter("commandtype");
        String esindex = request.getParameter("esindex");
        String estype = request.getParameter("estype");
        String treeId = request.getParameter("treeId");

        if(esindex==null||esindex.trim().isEmpty()){
            esindex="logstash";
        }
        if(estype==null||estype.trim().isEmpty()){
            estype=defaultType;
        }

        String result="";
        //设置查询条件
        Map<String,String> searchWords = new  HashMap<String,String> ();

        searchWords.put("domain",domain);
        searchWords.put("SearchType","or");
        searchWords.put("HightLight","no");
        searchWords.put("treeId",treeId);

        searchWords.put("localHostIp",localHostIp);
        searchWords.put("created_begain_more_info",created_begain_more_info);
        searchWords.put("created_begain_filter", commandtype);

        ESSerach esSerachs = new ESSerach();
        esSerachs.setSerachWord(searchWords);
        esSerachs.setSize(showMoreSize);


        if(commandtype.equals("gte")){
            esSerachs.setFrom(Integer.valueOf(downcount)*showMoreSize);
        }else{
            esSerachs.setFrom(Integer.valueOf(upcount)*showMoreSize);
        }



        esSerachs.setIndex(esindex);
        esSerachs.setType(estype);
        esSerachs.setOrderFile("created");

        //执行查询
        result =esService.SearchStringByFile(esSerachs);
        writeJson(result);
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
