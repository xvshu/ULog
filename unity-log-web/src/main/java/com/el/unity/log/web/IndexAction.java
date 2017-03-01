package com.el.unity.log.web;

import com.el.common.url.ElUrl;
import com.el.common.url.ElUrlUtils;
import com.el.unity.log.web.common.BaseAction;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;

/**
* Created by Administrator on 2015/5/9.
*/
public class IndexAction extends BaseAction {

    private String loginUrl;
    private String returnUrl;
    @Autowired
    private ElUrl elUrl;

    @Autowired
    private ElUrlUtils elUrlUtils;

    @Resource
    private IUnityLogMenuService unityLogMenuService;

    public static final int status = 1;
    private final static Log log = LogFactory.getLog(IndexAction.class);
    private String[] logoutCookie;

    public String execute() throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //login TODO
        return SUCCESS;
    }


    private String getVenderCurrentUrl(String uri) {
        ElUrl venderUrl = elUrlUtils.getElUrl("homeModule");
        venderUrl.getTarget(uri);
        return venderUrl.toString();
    }

    public String frame() throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        return SUCCESS;
    }

    public String logout() throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //logout TODO
        return SUCCESS;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public ElUrl getElUrl() {
        return elUrl;
    }

    public void setElUrl(ElUrl elUrl) {
        this.elUrl = elUrl;
    }
}
