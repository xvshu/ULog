package com.el.unity.log.service.domain.impl;


import com.el.unity.log.service.common.BaseService;
import com.el.unity.log.domain.domain.UnityLogDomain;
import com.el.unity.log.domain.domain.UnityLogDomainExample;
import com.el.unity.log.service.domain.IUnityLogDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UnityLogDomainService extends BaseService<UnityLogDomain, UnityLogDomainExample> implements IUnityLogDomainService {

    /**
     * 插入或者更新域名
     * @param unityLogDomain
     * @return
     */

    public int insertOrUpdateDomain(UnityLogDomain unityLogDomain){
        int modifyNum=0;
        //验重后添加或者更新
        UnityLogDomainExample unityLogDomainExample = new UnityLogDomainExample();
        unityLogDomainExample.or().andNameEqualTo(unityLogDomain.getName());
        List<UnityLogDomain> listForNew = selectByExample(unityLogDomainExample);

        if(null==listForNew || listForNew.size()<=0){
            unityLogDomain.setCreated(new Date());
            modifyNum=insert(unityLogDomain);
        }else{
            unityLogDomain.setModified(new Date());
            modifyNum=updateByPrimaryKeySelective(unityLogDomain);
        }
        return modifyNum;
    }
}