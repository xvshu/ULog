package com.el.unity.log.service.ip.impl;

import com.el.unity.log.service.common.BaseService;
import com.el.unity.log.domain.ip.UnityLogIp;
import com.el.unity.log.domain.ip.UnityLogIpExample;
import com.el.unity.log.service.ip.IUnityLogIpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnityLogIpService extends BaseService<UnityLogIp, UnityLogIpExample> implements IUnityLogIpService {


}