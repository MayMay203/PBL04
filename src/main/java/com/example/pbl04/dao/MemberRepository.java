package com.example.pbl04.dao;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Thanhvien,Integer> {
}