package com.acko.ClaimChain.ClaimChain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionDecryptionUtils {

    private static final String ALGORITHM = "AES";
    private static final String ENCODING = "UTF-8";
    private static final String SECURITY_KEY = "";

    // Generate a random AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // AES-128
        return keyGenerator.generateKey();
    }

    // Convert a string key to SecretKey
    public static SecretKeySpec getKeyFromString(String key) {
        return new SecretKeySpec(key.getBytes(), ALGORITHM);
    }

    // Encrypt the input data (DTO) into a Base64 encoded string
    public static <T> String encrypt(T dto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        // Encrypt the JSON string
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = getKeyFromString(SECURITY_KEY);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(json.getBytes(ENCODING));

        // Return encrypted data as a Base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the Base64 encoded string into the original data
    public static String  decrypt(String encryptedData ) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = getKeyFromString(SECURITY_KEY);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        String decryptedJson = new String(decryptedBytes, ENCODING);
        return decryptedJson;
    }
}
