package com.el.unity.log.web.es;

import com.el.common.result.Result;
import com.el.unity.log.web.common.BaseAction;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.service.menu.IUnityLogMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xvshu on 2016/5/17.
 */
public class IndexsAction  extends BaseAction {

    @Resource
    private IUnityLogMenuService unityLogMenuService;

    public String list() {
        String menId = request.getParameter("menId");
        UnityLogMenu thisMenu = getMenuByID(Long.valueOf(menId));
        Result result = new Result(true);
        result.addDefaultModel("menId",menId);
        result.addDefaultModel("esappenderurl",thisMenu.getEsAppenderUrl()==null?"":thisMenu.getEsAppenderUrl());
        result.addDefaultModel("esfileurl",thisMenu.getEsFileUrl()==null?"":thisMenu.getEsFileUrl());
        result.addDefaultModel("esperformsurl",thisMenu.getImgTimeUrl()==null?"":thisMenu.getImgTimeUrl());
        toVm(result);
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

    public IUnityLogMenuService getUnityLogMenuService() {
        return unityLogMenuService;
    }

    public void setUnityLogMenuService(IUnityLogMenuService unityLogMenuService) {
        this.unityLogMenuService = unityLogMenuService;
    }
}
