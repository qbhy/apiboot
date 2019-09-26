package com.qbhy.apiboot.framework.encryption;

import com.qbhy.apiboot.framework.contracts.encryption.DecryptException;
import com.qbhy.apiboot.framework.contracts.encryption.EncryptException;
import com.qbhy.apiboot.framework.contracts.encryption.Encrypter;
import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import com.qbhy.apiboot.framework.support.DriverException;
import com.qbhy.apiboot.framework.support.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EncrypterManager extends Manager<Encrypter> implements Encrypter {

    @Autowired
    SecretProvider secretProvider;

    /**
     * 设置驱动供应商
     */
    @Override
    public void setDriverProviders() {
        driverProviders = new HashMap<>();
        driverProviders.put("aes", () -> new AesEncrypter(secretProvider));
        driverProviders.put("des", () -> new DesEncrypter(secretProvider));
    }

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    @Override
    protected String defaultDriver() {
        return "aes";
    }

    @Override
    public String encrypt(String value) throws EncryptException {
        try {
            return driver().encrypt(value);
        } catch (DriverException e) {
            throw new EncryptException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String value) throws DecryptException {
        try {
            return driver().decrypt(value);
        } catch (DriverException e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
