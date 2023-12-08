package com.example.pbl04.dao;

import com.example.pbl04.entity.Dangky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegisRepository extends JpaRepository<Dangky,Integer> {
    @Query("select dk from Dangky dk where dk.maTK.id =:idTK and dk.maHD.id =:idHD")
    Dangky getDangKyByHDTK(Integer idTK, Integer idHD);
}