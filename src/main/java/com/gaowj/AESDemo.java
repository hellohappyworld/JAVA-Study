package com.gaowj;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;

/**
 * created by gaowj.
 * created on 2020-12-02.
 * function:
 * origin ->
 */
public class AESDemo {
    public static String encode(String data, String base64Key) throws GeneralSecurityException {
        final Key dataKey = new SecretKeySpec(Base64.decodeBase64(base64Key), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, dataKey);
        byte[] encryptData = cipher.doFinal(data.getBytes());

        return Base64.encodeBase64String(encryptData).replaceAll("\r", "")
                .replaceAll("\n", "");
    }

    public static void main(String[] args) throws GeneralSecurityException {
        String data = "868123039927020";
        String base64Key = "XGAXicVG5GMBsx5bueOe4w==";
        System.out.println(encode(data, base64Key));
    }
}
