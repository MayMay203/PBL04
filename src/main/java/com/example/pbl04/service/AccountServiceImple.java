package com.example.pbl04.service;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Taikhoan
    CheckLogin(String tenDN, String matKhau){
        return accountRepository.checkLogin(tenDN, matKhau);
    }
    public Taikhoan getTaiKhoanByID(Integer id) {
        Optional<Taikhoan> optionalTaikhoan = accountRepository.findById(id);
        return optionalTaikhoan.orElse(null);
    }

    @Override
    public void updateAvatar(String urlNew, Integer id) {
        accountRepository.updateAvatar(urlNew, id);
    }

    @Override
    public void addAccount(Taikhoan taikhoan) {
        accountRepository.save(taikhoan);
    }

    @Override
    public List<Taikhoan> getAccountAd() {
        return accountRepository.getAccountAd();
    }
    @Override
    public void changePassword(Taikhoan tk) {
        accountRepository.save(tk);
    }

    @Override
    public void changePasswordByOTP(Taikhoan tk, String mkMoi) {
        accountRepository.changePasswordByOTP(tk, mkMoi);
    }

    @Override
    public Thanhvien findMemberByEmail(String email) {
        return accountRepository.findMemberByEmail(email);
    }
}
