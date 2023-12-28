package com.example.pbl04.service;

import com.example.pbl04.dao.NotificationRepository;
import com.example.pbl04.entity.Thongbao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImple implements NotificationService{
    private final NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImple(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public void addNoticeOfSugg(Thongbao tb) {
        notificationRepository.save(tb);
    }

    @Override
    public List<Thongbao> getNotifiByIdAcc(Integer IdAcc) {
        return notificationRepository.getNotiByIdAcc(IdAcc);
    }
}
