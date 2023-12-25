package com.example.pbl04.service;

import com.example.pbl04.entity.*;
import java.util.List;

public interface RegisterService {
    void saveDK(Dangky dangky);
    void joinActivity(Integer maHD, Integer maTK);

    List<Dangky> getRegisterInforByIDAct(Integer IdAct);
    Dangky getDangKyByHDTK(Integer maTK, Integer maHD);
//    List<Thanhvien> getAllMyParticipants(Integer maTK);
}
