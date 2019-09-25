package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.hashing.SecretProvider;

public class Sha512Hasher extends BaseHasher {
    public Sha512Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * @return 算法名称
     */
    @Override
    public String algorithm() {
        return "SHA-512";
    }
}
