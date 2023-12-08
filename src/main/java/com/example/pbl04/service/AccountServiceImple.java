package com.example.pbl04.service;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Taikhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImple implements AccountService{

    private final AccountRepository accountRepository;
    @Autowired
    public AccountServiceImple(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Danhgia GetAccountByID(Integer ID) {
        return null;
    }
    @Override
    public Taikhoan CheckLogin(String tenDN, String matKhau){
        return accountRepository.checkLogin(tenDN, matKhau);
    }
    public Taikhoan getTaiKhoanByID(Integer id) {
        Optional<Taikhoan> optionalTaikhoan = accountRepository.findById(id);
        return optionalTaikhoan.orElse(null);
    }
}
