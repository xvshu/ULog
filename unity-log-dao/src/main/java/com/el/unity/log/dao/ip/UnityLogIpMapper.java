package com.el.unity.log.dao.ip;

import com.el.common.web.dao.BaseMapper;
import com.el.unity.log.domain.ip.UnityLogIp;
import com.el.unity.log.domain.ip.UnityLogIpExample;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityLogIpMapper extends BaseMapper<UnityLogIp,UnityLogIpExample> {
}