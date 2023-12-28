package com.example.pbl04.dao;
import com.example.pbl04.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Thongbao,Integer>{
    @Query("select tb from Thongbao tb where tb.maTK.id = :idAcc")
    List<Thongbao> getNotiByIdAcc(Integer idAcc);

    @Modifying
    @Query("update Thongbao tb set tb.trangThai = true where tb.id = :idNotice and tb.maTK.id = :idAcc")
    void updateStatusOfNotice(Integer idNotice,Integer idAcc);
}
