package com.el.unity.log.service.type.impl;

import com.el.unity.log.domain.type.UnityLogType;
import com.el.unity.log.domain.type.UnityLogTypeExample;
import com.el.unity.log.service.common.BaseService;
import com.el.unity.log.service.type.IUnityLogTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnityLogTypeService extends BaseService<UnityLogType,UnityLogTypeExample> implements IUnityLogTypeService {


}