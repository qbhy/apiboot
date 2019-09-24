package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.HashOptions;
import org.springframework.util.DigestUtils;

public class Md5Hasher extends AbstractHasher {

    /**
     * Hash the given value.
     *
     * @param value   需要哈希字符串
     * @param options 配置
     * @return string
     */
    @Override
    public String make(String value, HashOptions options) throws Throwable {
        return DigestUtils.md5DigestAsHex((value + options.getSecret()).getBytes());
    }
}
