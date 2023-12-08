package com.example.pbl04.dao;

import com.example.pbl04.entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Taikhoan,Integer>{
//   public Taikhoan getTKByID(Integer ID);
    @Query("select tk from Taikhoan tk where tk.tenDN = :tenDN and tk.matKhau = :matKhau")
    Taikhoan checkLogin(String tenDN, String matKhau);

    @Query("select tk from Taikhoan tk where tk.id = :id")
    Taikhoan getAccountByID(Integer id);
}
