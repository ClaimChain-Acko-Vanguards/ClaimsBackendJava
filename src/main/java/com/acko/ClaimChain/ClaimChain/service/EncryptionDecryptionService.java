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

    // Decrypt the encrypted DTO
    public static String decryptDto(String encryptedDto) throws Exception {

       // Class<?> dtoClass = Class.forName("");
        return EncryptionDecryptionUtils.decrypt(encryptedDto);
    }

   /* public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("a" , "sa");
        map.put("b" , "ba");
        try {
            String encryptedData = encryptDto(map);
            System.out.printf("encrypted data " + encryptedData);
            System.out.printf("De" + decryptDto(encryptedData));
        }catch (Exception ex){
            System.out.printf("aaaa" + ex.getMessage()) ;
        }
    }*/
}
