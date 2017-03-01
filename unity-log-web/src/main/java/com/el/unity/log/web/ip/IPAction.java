package com.el.unity.log.web.ip;

import com.alibaba.fastjson.JSON;
import com.el.common.page.PageList;
import com.el.unity.log.web.common.BaseAction;
import com.el.unity.log.common.utils.MSGStringUtil;
import com.el.unity.log.domain.ip.UnityLogIp;
import com.el.unity.log.domain.ip.UnityLogIpExample;
import com.el.unity.log.service.ip.IUnityLogIpService;
import com.el.unity.log.common.utils.EasyuiPageUtil;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class IPAction extends BaseAction {

    @Resource
    private IUnityLogIpService iUnityLogIpService;
    private UnityLogIp logIpForNew; //新增IP使用实体
    private String ipForListNew; //批量新增使用字符串


    public String index(){
        return "list";
    }



    /**
     * 按照分页获取IP
     * @return
     * @throws Exception
     */
    public String getAllLogIP()throws Exception{
        log.info("=getAllLogIP=>");
        //获取分页信息
        String pagesize = request.getParameter("rows");
        String page = request.getParameter("page");
        String searchWord = request.getParameter("searchWord");
        pagesize= pagesize==null||pagesize.length()==0?"10":pagesize;
        page= page==null||page.length()==0?"0":page;

        //组建分页查询对象
        UnityLogIpExample unityLogIpExample = new UnityLogIpExample();
        unityLogIpExample.setPageSize(Integer.valueOf(pagesize));
        unityLogIpExample.setPageIndex(Integer.valueOf(page));
        if(searchWord!=null && searchWord.trim().length()>0){
            unityLogIpExample.or().andIpLike('%'+searchWord.trim()+'%');
        }
        PageList<UnityLogIp> listAll = iUnityLogIpService.getPageList(unityLogIpExample);

        //分页结果转换为easyui要求的格式，写回前台
        EasyuiPageUtil easyuiPage = new EasyuiPageUtil();
        easyuiPage.setTotal(null == listAll ? 0 : listAll.getTotalItem());
        easyuiPage.setRows(null == listAll ? null : listAll.getList());
        writeJson(easyuiPage);
        log.info("<=getAllLogIP=>");
        return "list";
    }

    /**
     * 添加IP
     * @return
     */
    public String addLogIP( ){
        log.info("=addLogIP=>");
        int modifyNum=0;
        if(logIpForNew == null){
            writeJson(modifyNum);
        }else if(isRepeat(logIpForNew)){
            writeJson(MSGStringUtil.hasRepeat);
        }else{
            logIpForNew.setCreated(new Date());
            try{
                modifyNum = iUnityLogIpService.insert(logIpForNew);
            }catch (Exception e){
                log.error("=addLogIP=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=addLogIP=>");
        return "list";
    }

    /**
     * 修改IP
     * @return
     */
    public String editLogIP( ){
        log.info("=editLogIP=>");
        int modifyNum=0;
        if(logIpForNew == null){
            writeJson(modifyNum);
        }else if(isRepeatNotOurSelf(logIpForNew)){
            writeJson(MSGStringUtil.hasRepeat);
        }else{
            logIpForNew.setModified(new Date());
            try{
                modifyNum = iUnityLogIpService.updateByPrimaryKey(logIpForNew);
            }catch (Exception e){
                log.error("=editLogIP=>error:",e);
            }

            writeJson(modifyNum);
        }
        log.info("<=editLogIP=>");
        return "list";
    }

    /**
     * 删除IP
     * @return
     */
    public String deleteLogIP( ){
        log.info("=deleteLogIP=>");
        int modifyNum=0;
        String logIPId = request.getParameter("LogIPId");
        if(logIPId == null||logIPId.length()==0){
            writeJson(modifyNum);
        }else{
            try{
                modifyNum = iUnityLogIpService.deleteByPrimaryKey(Integer.valueOf(logIPId));
            }catch (Exception e){
                log.error("=deleteLogIP=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=deleteLogIP=>");
        return "list";
    }

    /**
     * 批量添加IP（前台传入为json格式）
     * @return
     */
    public String addListIp(){
        log.info("=addListIp=>");
        List<UnityLogIp> listForAdd =null;
        String returnStr = "success";
        if(null==ipForListNew || ipForListNew.trim().length()<=0){
            returnStr="您好，您未填写任何数据";
            writeJson(returnStr);
            return "list";
        }

        try{
            listForAdd= JSON.parseArray(ipForListNew, UnityLogIp.class);
        }catch (Exception e){
            log.error("=addListIp=>error:",e);
            returnStr="您好，您填写的数据不符合json格式";
            writeJson(returnStr);
            return "list";
        }

        //排重
        boolean hasRepeat=true;
        List<String> listRepeatIPS = new ArrayList<>();
        for(UnityLogIp oneIp : listForAdd){
            if(isRepeat(oneIp)){
                hasRepeat=false;
                listRepeatIPS.add(oneIp.getIp());
            }
        }

        if(!hasRepeat){
            returnStr=listRepeatIPS.toString()+"和已有数据重合";
            writeJson(returnStr);
            return "list";
        }

        try{
            for(UnityLogIp oneIp : listForAdd){
                oneIp.setCreated(new Date());
                iUnityLogIpService.insert(oneIp);
            }
        }catch (Exception e){
            log.error("=addListIp=>error:",e);
            returnStr="您好，添加出错，详情请看日志信息";
            writeJson(returnStr);
            return "list";
        }
        writeJson(returnStr);
        log.info("<=addListIp=>");
        return "list";
    }

    private boolean isRepeat(UnityLogIp unityLogIp){
        boolean hasRepeat = true;
        //组建分页查询对象
        UnityLogIpExample unityLogIpExample = new UnityLogIpExample();
        unityLogIpExample.or().andIpEqualTo(unityLogIp.getIp()).andDomainNameEqualTo(unityLogIp.getDomainName());
        List<UnityLogIp> listRe = iUnityLogIpService.selectByExample(unityLogIpExample);
        if (null == listRe || listRe.size()==0){
            hasRepeat=false;
        }
        return hasRepeat;
    }

    private boolean isRepeatNotOurSelf(UnityLogIp unityLogIp){
        boolean hasRepeat = true;
        //组建分页查询对象
        UnityLogIpExample unityLogIpExample = new UnityLogIpExample();
        unityLogIpExample.or().andIpEqualTo(unityLogIp.getIp()).andDomainNameEqualTo(unityLogIp.getDomainName()).andIdNotEqualTo(unityLogIp.getId());
        List<UnityLogIp> listRe = iUnityLogIpService.selectByExample(unityLogIpExample);
        if (null == listRe || listRe.size()==0){
            hasRepeat=false;
        }
        return hasRepeat;
    }


    public IUnityLogIpService getiUnityLogIpService() {
        return iUnityLogIpService;
    }

    public void setiUnityLogIpService(IUnityLogIpService iUnityLogIpService) {
        this.iUnityLogIpService = iUnityLogIpService;
    }

    public UnityLogIp getLogIpForNew() {
        return logIpForNew;
    }

    public void setLogIpForNew(UnityLogIp logIpForNew) {
        this.logIpForNew = logIpForNew;
    }

    public String getIpForListNew() {
        return ipForListNew;
    }

    public void setIpForListNew(String ipForListNew) {
        this.ipForListNew = ipForListNew;
    }

}
