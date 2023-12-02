package com.example.pbl04.dao;

import com.example.pbl04.entity.Hoatdong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Hoatdong,Integer> {
    @Query("SELECT h FROM Hoatdong h WHERE h.id = :id")
    Hoatdong findActivityByID(int id);

     @Query("SELECT h from Hoatdong h where h.tinhTrangHD = 3")
    List<Hoatdong> getActivityOccured();
     @Query("select h from Hoatdong h where h.tinhTrangHD = 1")
     List<Hoatdong> getActivityUpcomming();
     @Query("select count(*) from Hoatdong")
     Integer getNumActivity();
     @Query("SELECT count(*) from Dangky d where d.trangThai=true and d.phanQuyen=false")
     Integer getParticipants();
}
