package com.example.pbl04.service;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.dao.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.pbl04.entity.*;
@Service
public class NotificationServiceImple implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public NotificationServiceImple(NotificationRepository notificationRepository,
                                    AccountRepository accountRepository){
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public void addNotification(Thongbao tb) {
        notificationRepository.save(tb);
    }

    @Override
    public List<Thongbao> getNotifiByIdAcc(Integer IdAcc) {
        return notificationRepository.getNotiByIdAcc(IdAcc);
    }

    @Override
    @Transactional
    public void updateStatusOfNotice(Integer idNotice) {
        notificationRepository.updateStatusOfNotice(idNotice);
    }

    @Override
    public Integer countNotice(Integer idAcc) {
        return notificationRepository.countNotice(idAcc);
    }

}
