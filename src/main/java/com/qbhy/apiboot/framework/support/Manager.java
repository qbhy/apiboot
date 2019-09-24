package com.qbhy.apiboot.framework.support;

import java.util.HashMap;
import java.util.Map;

abstract public class Manager<T> {

    protected Map<String, T> drivers = new HashMap<>();

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    protected abstract String defaultDriver();

    public T driver() {
        return driver(defaultDriver());
    }

    public T driver(String name) {
        T driver = drivers.get(name);

        if (driver != null) {
            return driver;
        }

        driver = createDriver(name);

        drivers.put(name, driver);


        return driver;
    }

    protected T createDriver(String driver) {

        return null;
    }
}
