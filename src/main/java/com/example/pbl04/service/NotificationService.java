package com.example.pbl04.service;
import com.example.pbl04.entity.*;
import java.util.List;

public interface NotificationService {
    public void addNotification(Thongbao tb);
    public List<Thongbao> getNotifiByIdAcc(Integer IdAcc);
    public void updateStatusOfNotice(Integer idNotice,Integer idAcc);
}
