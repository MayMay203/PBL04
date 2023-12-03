package com.example.pbl04.service;


import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;

import java.util.List;

public interface ActivityService {
    Hoatdong getActivityByID(Integer id);
    List<Hoatdong> getActivityOccured();
    List<Hoatdong> getActivityUpcomming();
    List<Hoatdong> getActivityHappening();

    Integer getNumActivity();
    Integer getParticipants();
    Taikhoan getOrganizator(Integer id);
    Thanhvien getMemberByID(Integer id);
    List<Thanhvien> getMemberList(Integer id);

}
