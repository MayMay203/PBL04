package com.example.pbl04.dao;

import com.example.pbl04.entity.Dangky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisRepository extends JpaRepository<Dangky,Integer> {
    @Query("select dk from Dangky dk where dk.maHD.id = :IdAct and dk.phanQuyen=false ")
    List<Dangky> getRegisterInforByIDAct(Integer IdAct);
}