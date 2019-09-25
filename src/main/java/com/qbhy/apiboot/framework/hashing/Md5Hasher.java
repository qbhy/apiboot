package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;
import org.springframework.util.DigestUtils;

public class Md5Hasher extends AbstractHasher {

    public Md5Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * Hash the given value.
     *
     * @param value   需要哈希字符串
     * @return string
     */
    @Override
    public String make(String value) {
        return DigestUtils.md5DigestAsHex((value + secretProvider.get()).getBytes());
    }
}
