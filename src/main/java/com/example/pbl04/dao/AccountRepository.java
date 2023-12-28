package com.example.pbl04.dao;
import com.example.pbl04.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Taikhoan,Integer>{
//   public Taikhoan getTKByID(Integer ID);
    @Query("select tk from Taikhoan tk where tk.tenDN = :tenDN and tk.matKhau = :matKhau")
    Taikhoan checkLogin(String tenDN, String matKhau);

    @Query("select tk from Taikhoan tk where tk.id = :id")
    Taikhoan getAccountByID(Integer id);
//    @Transactional
    @Modifying
    @Query("update Taikhoan t set t.anhDaiDien = :avatarUrl where t.id = :accountId")
    void updateAvatar(@Param("avatarUrl") String avatarUrl, @Param("accountId") Integer accountId);

    @Query("select taikhoan from Taikhoan taikhoan where :username = taikhoan.tenDN")
    Taikhoan findByUsername(String username);
    @Modifying
    @Query("update Taikhoan t set t.matKhau = :password where t.id = :accountId")
    void TransAccount(Integer accountId, String password);

    @Query("select tk from Taikhoan tk where tk.loaiTK = true")
    List<Taikhoan> getAccountAd();
    @Modifying
    @Query("update Taikhoan t set t.matKhau = :mk where t.id = :accountId")
    boolean changePassword(@Param("mk") String mk, @Param("accountId") Integer accountId);
}
