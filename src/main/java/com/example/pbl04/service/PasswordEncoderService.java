package com.example.pbl04.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class PasswordEncoderService {
    public String encodePassword (String password)
    {
        return DigestUtils.md5Hex(password);
    }
}
