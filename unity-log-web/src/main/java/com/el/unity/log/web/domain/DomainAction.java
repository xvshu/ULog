package com.el.unity.log.web.domain;

import com.el.common.page.PageList;
import com.el.unity.log.common.utils.EasyuiPageUtil;
import com.el.unity.log.domain.domain.UnityLogDomain;
import com.el.unity.log.domain.domain.UnityLogDomainExample;
import com.el.unity.log.service.domain.IUnityLogDomainService;
import com.el.unity.log.web.common.BaseAction;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class DomainAction extends BaseAction {

    @Resource
    private IUnityLogDomainService unityLogDomainService;
    private UnityLogDomain domainForNew; //新增域名使用实体
    private String domainForListNew; //批量新增使用字符串


    public String index(){
        return "list";
    }



    /**
     * 按照分页获取域名
     * @return
     * @throws Exception
     */
    public String getPageDomain()throws Exception{

        log.info("=getPageDomain=>");
        PageList<UnityLogDomain> listAll=null;
        //获取分页信息
        String pagesize = request.getParameter("rows");
        String page = request.getParameter("page");
        String searchWord = request.getParameter("searchWord");
        pagesize= pagesize==null||pagesize.length()==0?"10":pagesize;
        page= page==null||page.length()==0?"0":page;

        //组建分页查询对象
        UnityLogDomainExample unityLogDomainExample = new UnityLogDomainExample();
        unityLogDomainExample.setPageSize(Integer.valueOf(pagesize));
        unityLogDomainExample.setPageIndex(Integer.valueOf(page));
        if(searchWord!=null && searchWord.trim().length()>0){
            unityLogDomainExample.or().andNameLike('%'+searchWord.trim()+'%');
        }
        try{
            listAll = unityLogDomainService.getPageList(unityLogDomainExample);
        }catch (Exception e){
            log.error("=getPageDomain=>error:",e);
        }

        //分页结果转换为easyui要求的格式，写回前台
        EasyuiPageUtil easyuiPage = new EasyuiPageUtil();
        easyuiPage.setTotal(null == listAll ? 0 : listAll.getTotalItem());
        easyuiPage.setRows(null == listAll ? null : listAll.getList());
        writeJson(easyuiPage);
        log.info("<=getPageDomain=>");
        return "list";
    }

    /**
     * 获取所有的域名-和其他页面级联用
     * @return
     */
    public void getAllDomain(){
        log.info("=getAllDomain=>");
        UnityLogDomainExample unityLogDomainExample = new UnityLogDomainExample();
        List<UnityLogDomain> listAll = unityLogDomainService.selectByExample(unityLogDomainExample);
        writeJson(listAll);
        log.info("<=getAllDomain=>");
    }


    /**
     * 判断域名是否重复
     * @return
     */
    public String isRepeat(){
        log.info("=isRepeat=>");
        int noRepeat = 0;//在数据库中没有重合
        int hasRepeat = 1;//在数据库中有重合
        String name = request.getParameter("name");
        UnityLogDomainExample unityLogDomainExample = new UnityLogDomainExample();
        unityLogDomainExample.or().andNameEqualTo(name);
        List<UnityLogDomain> listForNew = unityLogDomainService.selectByExample(unityLogDomainExample);
        if(null!=listForNew && listForNew.size()>0){
            writeJson(hasRepeat);
        }else{
            writeJson(noRepeat);
        }
        log.info("<=isRepeat=>");
        return "list";
    }

    /**
     * 添加域名
     * @return
     */
    public String addDomain( ){
        log.info("=addDomain=>");
        int modifyNum=0;
        if(domainForNew == null){
            writeJson(modifyNum);
        }else{
            modifyNum=unityLogDomainService.insertOrUpdateDomain(domainForNew);
            writeJson(modifyNum);
        }
        log.info("<=addDomain=>");
        return "list";
    }

    /**
     * 批量添加域名
     * @return
     */
    public String addListDomain(){
        log.info("=addListDomain=>");
        UnityLogDomain domainForAddList;
        String returnStr = "success";
        if(domainForListNew == null || domainForListNew.trim().length()==0){
            returnStr="请输入内容后提交！";
        }else{
            //用 | 符号切分域名
            String[] strDomainNames = domainForListNew.trim().split("\\|");

            //批量添加

            int modifyNum=0;
            for(int i = 0 ;i<strDomainNames.length;i++ ){
                String oneDomainName = strDomainNames[i];
                domainForAddList = new UnityLogDomain();
                domainForAddList.setName(oneDomainName);
                domainForAddList.setCreated(new Date());

                try{
                    modifyNum=unityLogDomainService.insertOrUpdateDomain(domainForAddList);
                }catch (Exception e){
                    log.error("=addListDomain=>error:",e);
                }

                if(modifyNum==0){
                    returnStr=returnStr+oneDomainName+",";
                }
            }

            if(returnStr!="success"){
                returnStr=returnStr+"新增失败！";
            }

            writeJson(returnStr);
        }
        log.info("<=addListDomain=>");
        return "list";
    }

    /**
     * 修改域名
     * @return
     */
    public String editUlogDomian( ){
        log.info("=editUlogDomian=>");
        int modifyNum=0;
        if(domainForNew == null){
            writeJson(modifyNum);
        }else{
            domainForNew.setModified(new Date());
            try{
                modifyNum = unityLogDomainService.updateByPrimaryKey(domainForNew);
            }catch (Exception e){
                log.error("=editUlogDomian=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=editUlogDomian=>");
        return "list";
    }

    /**
     * 删除域名
     * @return
     */
    public String deleteDomain( ){
        log.info("=deleteDomain=>");
        int modifyNum=0;
        String DomainName = request.getParameter("DomainName");
        if(DomainName == null||DomainName.length()==0){
            writeJson(modifyNum);
        }else{
            try{
                modifyNum = unityLogDomainService.deleteByPrimaryKey(DomainName);
            }catch (Exception e){
                log.error("=deleteDomain=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=deleteDomain=>");
        return "list";
    }


    public IUnityLogDomainService getUnityLogDomainService() {
        return unityLogDomainService;
    }

    public void setUnityLogDomainService(IUnityLogDomainService unityLogDomainService) {
        this.unityLogDomainService = unityLogDomainService;
    }

    public UnityLogDomain getDomainForNew() {
        return domainForNew;
    }

    public void setDomainForNew(UnityLogDomain domainForNew) {
        this.domainForNew = domainForNew;
    }

    public String getDomainForListNew() {
        return domainForListNew;
    }

    public void setDomainForListNew(String domainForListNew) {
        this.domainForListNew = domainForListNew;
    }

}
