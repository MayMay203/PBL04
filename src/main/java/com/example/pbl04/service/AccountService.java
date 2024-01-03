package com.example.pbl04.service;

import com.example.pbl04.entity.*;

import java.util.List;

public interface AccountService {
    Danhgia GetAccountByID(Integer ID);
    Taikhoan CheckLogin(String tenDN, String matKhau);
    Taikhoan getTaiKhoanByID(Integer id);
    void updateAvatar(String urlNew, Integer id);
    void addAccount(Taikhoan taikhoan);

    void changePassword(Taikhoan tk);

    void changePasswordByOTP( Taikhoan tk, String mkMoi);

    Thanhvien findMemberByEmail(String email);

    List<Taikhoan> getAccountAd();

    List<String> getAllEmail();
    List<String> getAllNumPhone();

}
