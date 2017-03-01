package com.el.unity.log.service.menu.impl;

import com.el.unity.log.service.common.BaseService;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import com.el.unity.log.service.menu.IUnityLogMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/9/21.
 */
@Service
@Transactional
public class UnityLogMenuService extends BaseService<UnityLogMenu,UnityLogMenuExample>  implements IUnityLogMenuService {

}
