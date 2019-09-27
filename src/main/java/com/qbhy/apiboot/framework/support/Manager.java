package com.qbhy.apiboot.framework.support;

import java.util.HashMap;
import java.util.Map;

abstract public class Manager<T> {

    protected Map<String, T> drivers = new HashMap<>();

    protected Map<String, DriverProvider<T>> driverProviders;

    /**
     * 设置驱动供应商
     */
    abstract public void setDriverProviders();

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    protected abstract String defaultDriver();

    /**
     * 使用默认驱动获取
     *
     * @return T
     */
    public T driver() throws DriverException {
        return driver(defaultDriver());
    }

    /**
     * 指定驱动获取
     *
     * @param name 驱动名称
     * @return T
     */
    public T driver(String name) throws DriverException {
        T driver = drivers.get(name != null ? name : defaultDriver());

        if (driver == null) {
            driver = createDriver(name);
            drivers.put(name, driver);
        }

        return driver;
    }

    /**
     * 创建驱动
     *
     * @param name 驱动名
     * @return 驱动
     * @throws DriverException 驱动异常
     */
    protected T createDriver(String name) throws DriverException {

        if (driverProviders == null) {
            setDriverProviders();
        }

        DriverProvider<T> provider = driverProviders.get(name);
        T driver;
        if (provider != null) {
            driver = provider.get();
            if (driver != null) {
                return driver;
            }
        }

        throw new DriverException(name + " Driver not found.");
    }

    /**
     * 添加自定义的驱动
     *
     * @param name     驱动名
     * @param provider 驱动提供者
     * @return this
     */
    public Manager<T> addProvider(String name, DriverProvider<T> provider) {
        if (name != null && provider != null) {
            driverProviders.put(name, provider);
        }

        return this;
    }
}
