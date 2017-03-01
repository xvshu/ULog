package com.el.unity.log.web.menu;

import com.alibaba.fastjson.JSON;
import com.el.common.page.PageList;
import com.el.common.result.Result;
import com.el.unity.log.web.common.BaseAction;
import com.el.unity.log.common.utils.EasyuiPageUtil;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import javax.annotation.Resource;

import java.util.*;

/**
 * Created by xvshu on 2015/9/22.
 *
 */
public class MenuAction extends BaseAction {

    @Resource
    private IUnityLogMenuService unityLogMenuService;

    private UnityLogMenu menuForNew;


    public String index(){
        log.info("=index=>");
        //取取所有的一级菜单
        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.or().andParentMenIdIsNull();
        List<UnityLogMenu> listAllOneLevelMenu = unityLogMenuService.selectByExample(unityLogMenuExample);
        Map<Long,String> oneLevelMenus = new LinkedHashMap<Long,String>();
        for(UnityLogMenu oneLevelMenu :listAllOneLevelMenu){
            oneLevelMenus.put(oneLevelMenu.getMenId(),oneLevelMenu.getName());
        }
        String oneLevelJsons = JSON.toJSONString(oneLevelMenus);
        Result result = new Result(true);
        result.addDefaultModel("oneLevelJsons",oneLevelJsons);
        toVm(result);
        log.info("<=index=>");
        return "list";
    }

    public String sysinfo(){
        return SUCCESS;
    }

    /**
     * 获取所有一级菜单
     * @return
     * @throws Exception
     */
    public String getFullAllMenu() throws Exception{
        log.info("=getFullAllMenu=>");
        //查询条件，父菜单id为空
        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.or().andParentMenIdIsNull();
        List<UnityLogMenu> listAllMenu = unityLogMenuService.selectByExample(unityLogMenuExample);
        EasyuiPageUtil easyuiPage = new EasyuiPageUtil();
        easyuiPage.setTotal(null == listAllMenu ? 0 : listAllMenu.size());
        easyuiPage.setRows(listAllMenu);
        writeJson(listAllMenu);
        log.info("<=getFullAllMenu=>");
        return "list";
    }

    /**
     * 按照分页获取菜单
     * @return
     * @throws Exception
     */
    public String getAllMenu()throws Exception{
        log.info("=getAllMenu=>");
        //获取分页信息
        String pagesize = request.getParameter("rows");
        String page = request.getParameter("page");
        String searchWord = request.getParameter("searchWord");
        pagesize= pagesize==null||pagesize.length()==0?"10":pagesize;
        page= page==null||page.length()==0?"0":page;

        //组建分页查询对象
        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.setPageSize(Integer.valueOf(pagesize));
        unityLogMenuExample.setPageIndex(Integer.valueOf(page));
        if(searchWord!=null && searchWord.trim().length()>0){
            unityLogMenuExample.or().andNameLike('%'+searchWord.trim()+'%');
        }

        PageList<UnityLogMenu> listAllMenu = unityLogMenuService.getPageList(unityLogMenuExample);

        //分页结果转换为easyui要求的格式，写回前台
        EasyuiPageUtil easyuiPage = new EasyuiPageUtil();
        easyuiPage.setTotal(null == listAllMenu ? 0 :listAllMenu.getTotalItem());
        easyuiPage.setRows(null == listAllMenu ? null :listAllMenu.getList());
        writeJson(easyuiPage);
        log.info("<=getAllMenu=>");
        return "list";
    }

    /**
     * 添加菜单
     * @return
     */
    public String addMenu( ){
        log.info("=addMenu=>");
        int modifyNum=0;
        if(menuForNew == null){
            writeJson(modifyNum);
        }else{
            menuForNew.setCreated(new Date());
            try{
                modifyNum = unityLogMenuService.insert(menuForNew);
            }catch (Exception e){
                log.error("=addMenu=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=addMenu=>");
        return "list";
    }

    /**
     * 修改菜单
     * @return
     */
    public String editMenu( ){
        log.info("=editMenu=>");
        int modifyNum=0;
        if(menuForNew == null){
            writeJson(modifyNum);
        }else{
            menuForNew.setModified(new Date());
            try{
                modifyNum = unityLogMenuService.updateByPrimaryKey(menuForNew);
            }catch (Exception e){
                log.error("=editMenu=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=editMenu=>");
        return "list";
    }

    /**
     * 删除菜单
     * @return
     */
    public String deleteMenu( ){
        log.info("=deleteMenu=>");
        int modifyNum=0;
        String menId = request.getParameter("menId");
        if(menId == null||menId.length()==0){
            writeJson(modifyNum);
        }else{
            try{
                modifyNum = unityLogMenuService.deleteByPrimaryKey(Integer.valueOf(menId));
            }catch (Exception e){
                log.error("=deleteMenu=>error:",e);
            }
            writeJson(modifyNum);
        }
        log.info("<=deleteMenu=>");
        return "list";
    }

    /**
     * 获取菜单树
     *  Map 格式 key为一级菜单名称，value为此菜单下所有的二级菜单（本系统只有两级菜单）
     * @return
     */
    public void getMenuTree(){
        log.info("=getMenuTree=>");
        //取取所有的一级菜单
        UnityLogMenuExample unityLogMenuExample = new UnityLogMenuExample();
        unityLogMenuExample.or().andParentMenIdIsNull();
        unityLogMenuExample.setOrderByClause("created");
        List<UnityLogMenu> listAllOneLevelMenu= new ArrayList<UnityLogMenu>();
        listAllOneLevelMenu= unityLogMenuService.selectByExample(unityLogMenuExample);

        //循环获取每个一级菜单下的子菜单
        Map<String,List<UnityLogMenu>> mapForAllMenu = new LinkedHashMap<String,List<UnityLogMenu>>();
        for(int i=0;i<listAllOneLevelMenu.size();i++){
            unityLogMenuExample.clear();
            unityLogMenuExample.or().andParentMenIdEqualTo(listAllOneLevelMenu.get(i).getMenId());
            List<UnityLogMenu> listAllNextLevelMenu =unityLogMenuService.selectByExample(unityLogMenuExample);
            mapForAllMenu.put(listAllOneLevelMenu.get(i).getName(),listAllNextLevelMenu);
        }
        writeJson(mapForAllMenu);
        log.info("<=getMenuTree=>");
    }

    public IUnityLogMenuService getUnityLogMenuService() {
        return unityLogMenuService;
    }

    public void setUnityLogMenuService(IUnityLogMenuService unityLogMenuService) {
        this.unityLogMenuService = unityLogMenuService;
    }

    public UnityLogMenu getMenuForNew() {
        return menuForNew;
    }

    public void setMenuForNew(UnityLogMenu menuForNew) {
        this.menuForNew = menuForNew;
    }


}


