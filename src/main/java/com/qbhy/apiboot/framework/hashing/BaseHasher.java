package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;
import com.qbhy.apiboot.framework.contracts.hashing.Hasher;
import org.apache.tomcat.util.buf.HexUtils;

import java.security.MessageDigest;

abstract public class BaseHasher implements Hasher {

    protected SecretProvider secretProvider;

    protected BaseHasher(SecretProvider secretProvider) {
        this.secretProvider = secretProvider;
    }

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
    public boolean check(String value, String hashedValue) throws Throwable {
        return MessageDigest.isEqual(make(value).getBytes(), hashedValue.getBytes());
    }

    /**
     * Check if the given hash has been hashed using the given options.
     *
     * @param hashedValue 哈希后的字符串
     * @return bool
     */
    @Override
    public boolean needsRehash(String hashedValue) {
        return false;
    }

    /**
     * @return 算法名称
     */
    abstract public String algorithm();

    /**
     * @param value 需要哈希字符串
     * @return 哈希后的字符串
     * @throws Throwable
     */
    @Override
    public String make(String value) throws Throwable {
        return HexUtils.toHexString(MessageDigest.getInstance(algorithm()).digest((value + secretProvider.get()).getBytes()));
    }
}
