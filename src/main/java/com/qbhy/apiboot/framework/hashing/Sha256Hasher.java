package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;

public class Sha256Hasher extends BaseHasher {
    public Sha256Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * @return 算法名称
     */
    @Override
    public String algorithm() {
        return "SHA-256";
    }
}
