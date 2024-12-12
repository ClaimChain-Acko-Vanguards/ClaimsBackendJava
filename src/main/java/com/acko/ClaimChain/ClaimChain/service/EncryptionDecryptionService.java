package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.utils.EncryptionDecryptionUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class EncryptionDecryptionService {

    private static final String SECRET_KEY = "yourSecretKey123"; // 16 characters for AES-128

    // Encrypt the DTO (in this case, a simple String for demo purposes)
    public static <T> String encryptDto(T dto) throws Exception {
        return EncryptionDecryptionUtils.encrypt(dto);
    }

    public static String decryptDto(String encryptedDto) throws Exception {
        return EncryptionDecryptionUtils.decrypt(encryptedDto);
    }
}
