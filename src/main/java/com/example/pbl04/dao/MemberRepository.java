package com.example.pbl04.dao;

import com.example.pbl04.entity.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Thanhvien,Integer> {
    @Query("SELECT h FROM Thanhvien h WHERE h.maTK.id = :maTK")
    Thanhvien findMemberByID(int maTK);
}