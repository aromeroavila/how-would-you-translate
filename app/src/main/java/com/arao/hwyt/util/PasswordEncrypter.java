package com.arao.hwyt.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

public class PasswordEncrypter {

	private static final String CIPHER_INSTANCE_TYPE = "PBEWithMD5AndDES";
    private static final String CHARSET_ENCODING_UTF_8 = "UTF-8";
	private static final char[] KEY = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_INSTANCE_TYPE);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance(CIPHER_INSTANCE_TYPE);
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes(CHARSET_ENCODING_UTF_8)));
    }

    public static String decrypt(String property) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_INSTANCE_TYPE);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance(CIPHER_INSTANCE_TYPE);
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)), CHARSET_ENCODING_UTF_8);
    }

    private static String base64Encode(byte[] bytes) throws UnsupportedEncodingException {
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        return URLEncoder.encode(encodedString, CHARSET_ENCODING_UTF_8);
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.decode(property, Base64.DEFAULT);
    }
}
