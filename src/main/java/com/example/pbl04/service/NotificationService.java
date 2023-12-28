package com.example.pbl04.service;

import com.example.pbl04.entity.Thongbao;

import java.util.List;

public interface NotificationService {
    public void addNoticeOfSugg(Thongbao tb);
    public List<Thongbao> getNotifiByIdAcc(Integer IdAcc);
}
