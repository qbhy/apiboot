package com.qbhy.apiboot.framework.encryption;

import com.qbhy.apiboot.framework.contracts.encryption.DecryptException;
import com.qbhy.apiboot.framework.contracts.encryption.EncryptException;
import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesEncrypter extends BaseEncrypter {

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public AesEncrypter(SecretProvider secretProvider) {
        super(secretProvider);
    }

    @Override
    public String encrypt(String value) throws EncryptException {
        try {
            byte[] password = getPassword();
            SecretKeySpec secretKeySpec = new SecretKeySpec(password, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);//"算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(password);//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64Utils.encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String value) throws DecryptException {
        try {
            byte[] password = getPassword();
            SecretKeySpec secretKeySpec = new SecretKeySpec(password, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(password);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            return new String(cipher.doFinal(Base64Utils.decodeFromString(value)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
