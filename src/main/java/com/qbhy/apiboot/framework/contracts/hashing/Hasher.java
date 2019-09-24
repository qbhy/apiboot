package com.qbhy.apiboot.framework.contracts.hashing;

public interface Hasher {

    /**
     * Get information about the given hashed value.
     *
     * @param hashedValue 散列值
     * @return array
     */
    public Object info(String hashedValue);

    /**
     * Hash the given value.
     *
     * @param value   需要哈希字符串
     * @param options 配置
     * @return string
     */
    public String make(String value, HashOptions options);

    /**
     * Check the given plain value against a hash.
     *
     * @param value       需要校验的字符串
     * @param hashedValue 哈希后的字符串
     * @param options     配置
     * @return bool
     */
    public boolean check(String value, String hashedValue,HashOptions options);

    /**
     * Check if the given hash has been hashed using the given options.
     *
     * @param hashedValue 哈希后的字符串
     * @param options     配置
     * @return bool
     */
    public boolean needsRehash(String hashedValue, HashOptions options);
}
