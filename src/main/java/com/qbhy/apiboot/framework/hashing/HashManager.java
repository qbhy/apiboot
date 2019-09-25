package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;
import com.qbhy.apiboot.framework.contracts.hashing.Hasher;
import com.qbhy.apiboot.framework.support.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HashManager extends Manager<Hasher> implements Hasher {

    @Autowired
    SecretProvider secretProvider;

    /**
     * 设置驱动供应商
     */
    @Override
    public void setDriverProviders() {
        driverProviders = new HashMap<>();
        driverProviders.put("md5", () -> new Md5Hasher(secretProvider));
        driverProviders.put("sha1", () -> new Sha1Hasher(secretProvider));
        driverProviders.put("sha256", () -> new Sha256Hasher(secretProvider));
        driverProviders.put("sha384", () -> new Sha384Hasher(secretProvider));
        driverProviders.put("sha512", () -> new Sha512Hasher(secretProvider));
    }

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    @Override
    protected String defaultDriver() {
        return "md5";
    }

    /**
     * Get information about the given hashed value.
     *
     * @param hashedValue 散列值
     * @return array
     */
    @Override
    public Object info(String hashedValue) throws Throwable {
        return driver().info(hashedValue);
    }

    /**
     * Hash the given value.
     *
     * @param value 需要哈希字符串
     * @return string
     */
    @Override
    public String make(String value) throws Throwable {
        return driver().make(value);
    }

    /**
     * Check the given plain value against a hash.
     *
     * @param value       需要校验的字符串
     * @param hashedValue 哈希后的字符串
     * @return bool
     */
    @Override
    public boolean check(String value, String hashedValue) throws Throwable {
        return driver().check(value, hashedValue);
    }

    /**
     * Check if the given hash has been hashed using the given options.
     *
     * @param hashedValue 哈希后的字符串
     * @return bool
     */
    @Override
    public boolean needsRehash(String hashedValue) throws Throwable {
        return driver().needsRehash(hashedValue);
    }
}
