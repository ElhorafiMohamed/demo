package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class PasswordGenerator {
    private static final int TEMP_PASSWORD_LENGTH = 12;

    public static String generateTemporaryPassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[TEMP_PASSWORD_LENGTH];
        secureRandom.nextBytes(randomBytes);

        // Convert the random bytes to a Base64-encoded string
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
