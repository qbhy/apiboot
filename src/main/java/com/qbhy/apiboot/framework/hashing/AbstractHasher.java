package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.HashOptions;
import com.qbhy.apiboot.framework.contracts.hashing.Hasher;

import java.security.MessageDigest;

abstract public class AbstractHasher implements Hasher {

    /**
     * Get information about the given hashed value.
     *
     * @param hashedValue 散列值
     * @return array
     */
    @Override
    public Object info(String hashedValue) {
        return null;
    }

    @Override
    public boolean check(String value, String hashedValue, HashOptions options) throws Throwable {
        return MessageDigest.isEqual(make(value, options).getBytes(), hashedValue.getBytes());
    }

    /**
     * Check if the given hash has been hashed using the given options.
     *
     * @param hashedValue 哈希后的字符串
     * @param options     配置
     * @return bool
     */
    @Override
    public boolean needsRehash(String hashedValue, HashOptions options) {
        return false;
    }
}
