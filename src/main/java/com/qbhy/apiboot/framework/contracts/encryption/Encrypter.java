package com.qbhy.apiboot.framework.contracts.encryption;

public interface Encrypter {
    public String encrypt(String value) throws EncryptException;

    public String decrypt(String value) throws DecryptException;
}
