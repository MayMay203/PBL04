package com.example.pbl04.service;


import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;

import java.util.List;

public interface ActivityService {
    List<Hoatdong> getAllActivity();
    Hoatdong getActivityByID(Integer id);
    List<Hoatdong> getActivityOccured();
    List<Dangky> getActivityUpcomming();
    List<Hoatdong> getActivityHappening();
    Dangky getDateRegis(Integer mahd);
    Integer getNumActivity();
    Integer getParticipants();
    Taikhoan getOrganizator(Integer id);
    Thanhvien
    getMemberByID(Integer id);
    List<Thanhvien> getMemberList(Integer id);
    List<Hoatdong> getActivityByMyID(Integer myID);
    //Danh gia
    List<Hoatdong> getActivityByMember(Integer id);
    List<Hoatdong> getActivityByHost(Integer id);

    Dangky getRegisterInfo(Integer IdAct);
//    Instant getTimeResgister(Integer IdAct);

    List<Hoatdong> getActOccuredByName(String nameAct);

    List<Hoatdong> getActOfMemberByName(Integer idAcc,String nameAct);

}