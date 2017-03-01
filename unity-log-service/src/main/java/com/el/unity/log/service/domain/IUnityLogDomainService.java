package com.el.unity.log.service.domain;

import com.el.common.web.service.IBaseService;
import com.el.unity.log.domain.domain.UnityLogDomain;
import com.el.unity.log.domain.domain.UnityLogDomainExample;
import com.el.unity.log.domain.ip.UnityLogIp;
import com.el.unity.log.domain.ip.UnityLogIpExample;

public interface IUnityLogDomainService extends IBaseService<UnityLogDomain,UnityLogDomainExample>{

    /**
     * 插入或者更新域名
     * @param unityLogDomain
     * @return
     */
    public int insertOrUpdateDomain(UnityLogDomain unityLogDomain);
}