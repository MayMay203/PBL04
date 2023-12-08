package com.example.pbl04.service;

import com.example.pbl04.entity.Dangky;


public interface RegisterService {
    void saveDK(Dangky dangky);
    void joinActivity(String maHD, String maTK);
}
