package com.example.pbl04.service;

import com.example.pbl04.entity.*;

import java.util.List;

public interface AccountService {
    Danhgia GetAccountByID(Integer ID);
    Taikhoan CheckLogin(String tenDN, String matKhau);
    Taikhoan getTaiKhoanByID(Integer id);
    void updateAvatar(String urlNew, Integer id);
    Taikhoan addAccount(Taikhoan taikhoan);

    List<Taikhoan> getAccountAd();
}
