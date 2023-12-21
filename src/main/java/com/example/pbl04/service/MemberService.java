package com.example.pbl04.service;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Thanhvien;

import java.util.List;

public interface MemberService {
    List<Thanhvien> getAllMember();
    Thanhvien findMemberByID(int maTK);
    List<Thanhvien> getMembersByRegis(List<Dangky> registerList);

    void updateInfor(Thanhvien thanhvien);
}
