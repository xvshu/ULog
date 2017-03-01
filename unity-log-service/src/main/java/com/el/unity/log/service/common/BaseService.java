package com.el.unity.log.service.common;

import com.el.common.web.model.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * Created by xvshu on 2016/5/19.
 */
public class BaseService<T extends Serializable, E extends BaseModel> extends com.el.common.web.service.impl.BaseService<T, E> {
    protected final Logger log = LogManager.getLogger(this.getClass().getName());
}
