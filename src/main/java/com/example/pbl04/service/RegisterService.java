package com.example.pbl04.service;


import com.example.pbl04.entity.Dangky;

public interface RegisterService {
    void saveDK(Dangky dangky);
    void joinActivity(Integer maHD, Integer maTK);
    Dangky getDangKyByHDTK(Integer maTK, Integer maHD);
}
