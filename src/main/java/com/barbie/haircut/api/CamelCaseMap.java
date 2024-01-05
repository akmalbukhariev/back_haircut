package com.barbie.haircut.api;

import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

public class CamelCaseMap extends HashMap<String, Object> {
    private static final long serialVersionUID = -7826221370286387542L;

    public CamelCaseMap() {
    }

    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }
}
