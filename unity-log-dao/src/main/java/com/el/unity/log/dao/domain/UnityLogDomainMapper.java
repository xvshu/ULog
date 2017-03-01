package com.el.unity.log.dao.domain;

import com.el.common.web.dao.BaseMapper;
import com.el.unity.log.domain.domain.UnityLogDomain;
import com.el.unity.log.domain.domain.UnityLogDomainExample;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityLogDomainMapper extends BaseMapper<UnityLogDomain,UnityLogDomainExample> {
}