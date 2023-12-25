package com.example.pbl04.service;


import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Thanhvien;

import java.util.List;

import java.util.List;

public interface RegisterService {
    void saveDK(Dangky dangky);
    void joinActivity(Integer maHD, Integer maTK);

    List<Dangky> getRegisterInforByIDAct(Integer IdAct);
    Dangky getDangKyByHDTK(Integer maTK, Integer maHD);

    Dangky getRegisterIsMember(Integer maHD, Integer maTK);
//    List<Thanhvien> getAllMyParticipants(Integer maTK);
}
