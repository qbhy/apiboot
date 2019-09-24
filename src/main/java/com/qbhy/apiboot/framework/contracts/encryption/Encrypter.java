package com.qbhy.apiboot.framework.contracts.encryption;

public interface Encrypter {
    public String encrypt() throws EncryptException;

    public String decrypt()throws DecryptException;
}
