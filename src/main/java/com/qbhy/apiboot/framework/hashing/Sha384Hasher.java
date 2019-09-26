package com.qbhy.apiboot.framework.hashing;

import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;

public class Sha384Hasher extends BaseHasher {
    public Sha384Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * @return 算法名称
     */
    @Override
    public String algorithm() {
        return "SHA-384";
    }
}
