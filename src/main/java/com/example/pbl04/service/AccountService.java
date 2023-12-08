package com.example.pbl04.service;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Taikhoan;

public interface AccountService {
    Danhgia GetAccountByID(Integer ID);
    Taikhoan CheckLogin(String tenDN, String matKhau);
    Taikhoan getTaiKhoanByID(Integer id);
}
