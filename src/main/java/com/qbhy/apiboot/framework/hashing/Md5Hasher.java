package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;

public class Md5Hasher extends BaseHasher {

    public Md5Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * @return 算法名称
     */
    @Override
    public String algorithm() {
        return "MD5";
    }
}
