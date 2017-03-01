package com.el.unity.log.web.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by xvshu on 2016/5/19.
 */
public class BaseAction extends com.el.common.web.action.BaseAction {
    protected final Logger log = LogManager.getLogger(this.getClass().getName());
}
