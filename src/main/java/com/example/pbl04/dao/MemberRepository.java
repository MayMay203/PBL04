package com.example.pbl04.dao;

import com.example.pbl04.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Thanhvien,Integer> {
    @Query("SELECT h FROM Thanhvien h WHERE h.maTK.id = :maTK")
    Thanhvien findMemberByID(int maTK);
    @Query("select tv from Dangky dk, Hoatdong hd,Thanhvien tv where dk.maHD.id= hd.id and hd.id=:maHD and dk.maTK.id= tv.maTK.id and dk.trangThai=false and dk.phanQuyen=false")
    List<Thanhvien> getMemberNeedConfirmByIDHD(Integer maHD);
    @Transactional
    @Modifying
    @Query("update Dangky dk set dk.trangThai=true where dk.maHD.id=:maHD and dk.maTK.id=:maTK")
    void ConfirmMember(Integer maHD, Integer maTK);
    @Transactional
    @Modifying
    @Query("update Dangky dk set dk.trangThai=false where dk.maHD.id=:maHD and dk.maTK.id=:maTK")
    void CancelMember(Integer maHD, Integer maTK);
}