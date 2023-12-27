package com.example.pbl04.service;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.entity.Taikhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private PasswordEncoderService passwordEncoderService;
    @Autowired
    private AccountRepository accountRepository;
    public Taikhoan authenticateAccount(String username,String password){
        Taikhoan user = accountRepository.findByUsername(username);
        if(user!=null){
            String encodeedPassword= user.getMatKhau();
            if (passwordEncoderService.checkPassword(password, encodeedPassword)) {
                return user;
            }
        }
        return null;
    }
}
