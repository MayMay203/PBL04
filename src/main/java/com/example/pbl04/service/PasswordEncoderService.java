package com.example.pbl04.service;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoderService {
    public String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return encodePassword(rawPassword).equals(encodedPassword);
    }

}
