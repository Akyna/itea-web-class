package com.itea.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoding {
    private static final String SALT = "com.itea";
    public static String md5Encryption(String inputData) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(StandardCharsets.UTF_8.encode(inputData));
            result = String.format("%032x", new BigInteger(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String md5EncryptionWithSalt(String inputData) {
        return md5Encryption(inputData + SALT);
    }
}
