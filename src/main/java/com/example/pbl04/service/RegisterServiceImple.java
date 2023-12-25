package com.example.pbl04.service;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.dao.RegisRepository;
import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class RegisterServiceImple implements RegisterService {
    private final RegisRepository regisRepository;
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    @Autowired
    public RegisterServiceImple(RegisRepository regisRepository, AccountRepository accountRepository, ActivityRepository activityRepository) {
        this.regisRepository = regisRepository;
        this.accountRepository = accountRepository;
        this.activityRepository = activityRepository;
    }
    @Override
    public void saveDK(Dangky dangky) {
        regisRepository.save(dangky);
    }
    public void joinActivity(Integer maHD, Integer maTK) {
        Dangky dangky = new Dangky();
        Taikhoan taikhoan = accountRepository.getAccountByID(maTK);
        Hoatdong hoatdong = activityRepository.getActivityByID(maHD);
        dangky.setMaTK(taikhoan);
        dangky.setMaHD(hoatdong);
        dangky.setPhanQuyen(false);
        dangky.setTrangThai(false);
        dangky.setThoiGianDK(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        regisRepository.save(dangky);
    }
    public Dangky getDangKyByHDTK(Integer maTK, Integer maHD) {return regisRepository.getDangKyByHDTK(maTK,maHD);}

    @Override
    public Dangky getRegisterIsMember(Integer maHD, Integer maTK) {
        return regisRepository.getRegisterIsMember(maHD, maTK);
    }

    @Override
    public List<Dangky> getRegisterInforByIDAct(Integer IdAct) {
        return regisRepository.getRegisterInforByIDAct(IdAct);
    }
//    public List<Thanhvien> getAllMyParticipants(Integer maTK) {return regisRepository.getAllMyParticipants(maTK);}
}
