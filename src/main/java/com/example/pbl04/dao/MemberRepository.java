package com.example.pbl04.dao;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<Thanhvien,Integer> {
//    @Query("SELECT h FROM Thanhvien h WHERE h.id = :id")
//    Hoatdong findMemberByID(int id);
}