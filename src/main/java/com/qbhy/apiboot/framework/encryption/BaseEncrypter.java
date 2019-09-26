package com.qbhy.apiboot.framework.encryption;

import com.qbhy.apiboot.framework.contracts.encryption.Encrypter;
import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;

import java.nio.charset.StandardCharsets;

abstract public class BaseEncrypter implements Encrypter {

    protected SecretProvider secretProvider;

    BaseEncrypter(SecretProvider secretProvider) {
        this.secretProvider = secretProvider;
    }

    public byte[] getPassword() {
        return assemblePassword(secretProvider.get()).getBytes(StandardCharsets.UTF_8);
    }

    public static String assemblePassword(String secret) {
        int len = secret.length();
        if (len == 16) {
            return secret;
        }
        if (len > 16) {
            return secret.substring(0, 16);
        }


        return secret + "abcdef0123456789".substring(0, 16 - len);
    }
}
