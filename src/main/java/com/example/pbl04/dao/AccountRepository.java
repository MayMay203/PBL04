package com.example.pbl04.dao;
import com.example.pbl04.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
