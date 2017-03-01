package com.el.unity.log.web.type;

import com.alibaba.fastjson.JSON;
import com.el.common.page.PageList;
import com.el.unity.log.common.utils.EasyuiPageUtil;
import com.el.unity.log.common.utils.MSGStringUtil;
import com.el.unity.log.domain.type.UnityLogType;
import com.el.unity.log.domain.type.UnityLogTypeExample;
import com.el.unity.log.service.type.IUnityLogTypeService;
import com.el.unity.log.web.common.BaseAction;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class TypeAction extends BaseAction {

    @Resource
    private IUnityLogTypeService unityLogTypeService;

//    @Resource
//    private ILogicRecordService logicRecordService;

    private UnityLogType unityLogTypeForNew;
    private String typeForListNew;


    public String index(){
        return "list";
    }


    public String TestLog4j2(){
//        System.out.println("=TestLog4j2=>teid:"+MDC.get("teid"));
//
//        System.out.println("=TestLog4j2=>rtid:"+MDC.get("rtid"));
//
//        PublicResult<Double> publicResult= logicRecordService.wmpsLeavingMoney(1);
//        System.out.println(JSON.toJSONString(publicResult));
        return "list";
    }

    /**
     * 按照分页获取类型
     * @return
     * @throws Exception
     */
    public String getPageType()throws Exception{
        log.info("=getPageType=>");
        //获取分页信息
        String pagesize = request.getParameter("rows");
        String page = request.getParameter("page");
        String searchWord = request.getParameter("searchWord");
        pagesize= pagesize==null||pagesize.length()==0?"10":pagesize;
        page= page==null||page.length()==0?"0":page;

        //组建分页查询对象
        UnityLogTypeExample unityLogTypeExample = new UnityLogTypeExample();
        unityLogTypeExample.setPageSize(Integer.valueOf(pagesize));
        unityLogTypeExample.setPageIndex(Integer.valueOf(page));
        if(searchWord!=null && searchWord.trim().length()>0){
            unityLogTypeExample.or().andTypeNameLike('%'+searchWord.trim()+'%');
        }
        PageList<UnityLogType> listAll = unityLogTypeService.getPageList(unityLogTypeExample);

        //分页结果转换为easyui要求的格式，写回前台
        EasyuiPageUtil easyuiPage = new EasyuiPageUtil();
        easyuiPage.setTotal(null == listAll ? 0 : listAll.getTotalItem());
        easyuiPage.setRows(null == listAll ? null :listAll.getList());
        writeJson(easyuiPage);
        log.info("<=getPageType=>");
        return "list";
    }

    /**
     * 添加类型
     * @return
     */
    public String addType( ){
        log.info("=addType=>");
        int modifyNum=0;
        if(unityLogTypeForNew == null){
            writeJson(modifyNum);
        }else if(isRepeat(unityLogTypeForNew)){
            writeJson(MSGStringUtil.hasRepeat);
        }else{
            unityLogTypeForNew.setCreated(new Date());
            try{
                modifyNum = unityLogTypeService.insert(unityLogTypeForNew);
            }catch (Exception e){
                log.error("=addType=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=addType=>");
        return "list";
    }

    /**
     * 修改类型
     * @return
     */
    public String editType( ){
        log.info("=editType=>");
        int modifyNum=0;
        if(unityLogTypeForNew == null){
            writeJson(modifyNum);
        }else if(isRepeatNotOurSelf(unityLogTypeForNew)){
            writeJson(MSGStringUtil.hasRepeat);
        }else{
            unityLogTypeForNew.setModified(new Date());
            try{
                modifyNum = unityLogTypeService.updateByPrimaryKey(unityLogTypeForNew);
            }catch (Exception e){
                log.error("=editType=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=editType=>");
        return "list";
    }

    /**
     * 删除类型
     * @return
     */
    public String deleteType( ){
        log.info("=deleteType=>");
        int modifyNum=0;
        String typeId = request.getParameter("typeId");
        if(typeId == null||typeId.length()==0){
            writeJson(modifyNum);
        }else{
            try{
                modifyNum = unityLogTypeService.deleteByPrimaryKey(Integer.valueOf(typeId));
            }catch (Exception e){
                log.error("=deleteType=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=deleteType=>");
        return "list";
    }

    /**
     * 批量添加类型
     * @return
     */
    public String addListType(){
        log.info("=addListType=>");
        List<UnityLogType> listForAdd =null;
        String returnStr = "success";
        if(null==typeForListNew || typeForListNew.trim().length()<=0){
            returnStr="您好，您未填写任何数据";
            writeJson(returnStr);
            return "list";
        }

        try{
            listForAdd= JSON.parseArray(typeForListNew, UnityLogType.class);
        }catch (Exception e){
            log.error("=addListType=>error:", e);
            returnStr ="您好，您填写的数据不符合json格式";
            writeJson(returnStr);
            return "list";
        }

        //排重
        boolean hasRepeat=true;
        List<String> listRepeatIDS = new ArrayList<>();
        for(UnityLogType oneType : listForAdd){
            if(isRepeat(oneType)){
                hasRepeat=false;
                listRepeatIDS.add(oneType.getTypeName());
            }
        }

        if(!hasRepeat){
            returnStr=listRepeatIDS.toString()+"和已有数据重合";
            writeJson(returnStr);
            return "list";
        }

        try{
            for(UnityLogType oneType : listForAdd){
                oneType.setCreated(new Date());
                unityLogTypeService.insert(oneType);
            }
        }catch (Exception e){
            log.error("=addListType=>error:", e);
            returnStr="您好，添加出错，详情请看日志信息";
            writeJson(returnStr);
            return "list";
        }
        writeJson(returnStr);
        log.info("<=addListType=>");
        return "list";
    }


    private boolean isRepeat(UnityLogType unityLogType){
        boolean hasRepeat = true;
        //组建分页查询对象
        UnityLogTypeExample unityLogTypeExample = new UnityLogTypeExample();
        unityLogTypeExample.or().andTypeNameEqualTo(unityLogType.getTypeName()).andDomainNameEqualTo(unityLogType.getDomainName());
        List<UnityLogType> listRe = unityLogTypeService.selectByExample(unityLogTypeExample);
        if (null == listRe || listRe.size()==0){
            hasRepeat=false;
        }
        return hasRepeat;
    }

    private boolean isRepeatNotOurSelf(UnityLogType unityLogType){
        boolean hasRepeat = true;
        //组建分页查询对象
        UnityLogTypeExample unityLogTypeExample = new UnityLogTypeExample();
        unityLogTypeExample.or().andTypeNameEqualTo(unityLogType.getTypeName()).andDomainNameEqualTo(unityLogType.getDomainName()).andIdNotEqualTo(unityLogType.getId());
        List<UnityLogType> listRe = unityLogTypeService.selectByExample(unityLogTypeExample);
        if (null == listRe || listRe.size()==0){
            hasRepeat=false;
        }
        return hasRepeat;
    }


    public IUnityLogTypeService getUnityLogTypeService() {
        return unityLogTypeService;
    }

    public void setUnityLogTypeService(IUnityLogTypeService unityLogTypeService) {
        this.unityLogTypeService = unityLogTypeService;
    }

    public UnityLogType getUnityLogTypeForNew() {
        return unityLogTypeForNew;
    }

    public void setUnityLogTypeForNew(UnityLogType unityLogTypeForNew) {
        this.unityLogTypeForNew = unityLogTypeForNew;
    }

    public String getTypeForListNew() {
        return typeForListNew;
    }

    public void setTypeForListNew(String typeForListNew) {
        this.typeForListNew = typeForListNew;
    }

//    public ILogicRecordService getLogicRecordService() {
//        return logicRecordService;
//    }
//
//    public void setLogicRecordService(ILogicRecordService logicRecordService) {
//        this.logicRecordService = logicRecordService;
//    }


}
