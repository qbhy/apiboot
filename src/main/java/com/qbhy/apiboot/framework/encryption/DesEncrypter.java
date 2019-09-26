package com.qbhy.apiboot.framework.encryption;

import com.qbhy.apiboot.framework.contracts.encryption.DecryptException;
import com.qbhy.apiboot.framework.contracts.encryption.EncryptException;
import com.qbhy.apiboot.framework.contracts.kernel.SecretProvider;
import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class DesEncrypter extends BaseEncrypter {
    public DesEncrypter(SecretProvider secretProvider) {
        super(secretProvider);
    }

    @Override
    public String encrypt(String value) throws EncryptException {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(getPassword());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(desKey), random);
            byte[] byteContent = value.getBytes(StandardCharsets.UTF_8);
            return HexUtils.toHexString(cipher.doFinal(byteContent));
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String value) throws DecryptException {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(getPassword());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(desKey), random);
            byte[] byteContent = HexUtils.fromHexString(value);
            assert byteContent != null;
            return new String(cipher.doFinal(byteContent));
        } catch (Exception e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
