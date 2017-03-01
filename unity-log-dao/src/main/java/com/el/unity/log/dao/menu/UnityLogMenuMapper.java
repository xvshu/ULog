package com.el.unity.log.dao.menu;

import com.el.common.web.dao.BaseMapper;
import com.el.unity.log.domain.menu.UnityLogMenu;
import com.el.unity.log.domain.menu.UnityLogMenuExample;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityLogMenuMapper extends BaseMapper<UnityLogMenu,UnityLogMenuExample> {
}