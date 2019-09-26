package com.qbhy.apiboot.framework.encryption;

import com.qbhy.apiboot.framework.contracts.encryption.DecryptException;
import com.qbhy.apiboot.framework.contracts.encryption.EncryptException;
import com.qbhy.apiboot.framework.contracts.encryption.Encrypter;
import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESEncrypter implements Encrypter {

    private SecretProvider secretProvider;

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NOPadding";

    AESEncrypter(SecretProvider secretProvider) {
        this.secretProvider = secretProvider;
    }

    @Override
    public String encrypt(String value) throws EncryptException {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = value.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(secretProvider.get().getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(secretProvider.get().getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            return Base64.encodeBase64String(cipher.doFinal(plaintext));
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String value) throws DecryptException {
        try {
            byte[] password = secretProvider.get().getBytes();
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(password);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] result = cipher.doFinal(Base64.decodeBase64(value.getBytes()));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
