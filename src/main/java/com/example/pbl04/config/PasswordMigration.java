package com.example.pbl04.config;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.PasswordEncoderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PasswordMigration {
    @Autowired
    private PasswordEncoderService passwordEncoderService;
    private AccountRepository accountRepository;
    public PasswordMigration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Transactional
    public void tranPassword()
    {
        List<Taikhoan> taikhoanList= accountRepository.findAll();
        for(Taikhoan taikhoan : taikhoanList){
            Integer id = taikhoan.getId();
            String password= taikhoan.getMatKhau();
            String md5Password=passwordEncoderService.encodePassword(password);
            accountRepository.TransAccount(id,md5Password);
        }
    }
}
