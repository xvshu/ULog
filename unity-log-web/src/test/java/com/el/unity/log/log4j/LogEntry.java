package com.el.unity.log.log4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xvshu on 2016/6/17.
 */
public class LogEntry {

    private Map<String, Object> map = new HashMap<String, Object>();

    public final <VT> void put(String key, VT value) {
        map.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public final <VT> VT get(String key) {
        return (VT) map.get(key);
    }

    public final <VT> boolean contains(String key) {
        return map.containsKey(key);
    }

    public String toString() {
        return map.toString();
    }

}
