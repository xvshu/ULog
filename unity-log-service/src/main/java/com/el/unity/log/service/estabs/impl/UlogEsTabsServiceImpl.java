/*
 * Powered By [rapid-framework]
 * Web Site: http://www.eloancn.com
 * By:eloancn
 * Since 2015 - 2016
 */

package com.el.unity.log.service.estabs.impl;

import com.el.common.debug.DebugHelper;
import com.el.common.debug.IDebugService;
import com.el.common.web.service.impl.BaseService;
import com.el.unity.log.domain.estabs.UlogEsTabs;
import com.el.unity.log.domain.estabs.UlogEsTabsExample;
import com.el.unity.log.service.estabs.IUlogEsTabsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("ulogEsTabsService")
@Transactional
public class UlogEsTabsServiceImpl extends  BaseService<UlogEsTabs, UlogEsTabsExample> implements IUlogEsTabsService, IDebugService {
	
	@Override
    public String execute(String s, Object... objects) {
        return DebugHelper.proxyExec(this, s, new DebugHelper.ReverseExecute() {
            @Override
            public String execute(String s, Map map) {
                return null;
            }
        });
    }
}
