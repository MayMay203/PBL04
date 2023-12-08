package com.example.pbl04.service;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.dao.TopicRepository;
import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final TopicRepository topicRepository;
    private final AccountService accountService;
    private final RegisterService registerService;
    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, TopicRepository topicRepository, AccountService accountService, RegisterService registerService) {
        this.activityRepository = activityRepository;
        this.topicRepository = topicRepository;
        this.accountService = accountService;
        this.registerService = registerService;
    }

    @Override
    public List<Hoatdong> getAllActivity() {
        return activityRepository.findAll();
    }

    @Override
    public Hoatdong getActivityByID(Integer id)
    {
        return activityRepository.findActivityByID(id);
    }
    @Override
    public List<Hoatdong> getActivityOccured()
    {
        return activityRepository.getActivityOccured();
    }
    public List<Dangky> getActivityUpcomming(){return activityRepository.getActivityUpcomming();}
    public List<Hoatdong> getActivityHappening() {return activityRepository.getActivityHappening();}
    public Integer getNumActivity(){return activityRepository.getNumActivity();}
    public Dangky  getDateRegis(Integer mahd){return activityRepository.getDateRegis();}
    public Integer getParticipants() {return activityRepository.getParticipants();}
    public Taikhoan getOrganizator(Integer id){ return activityRepository.getOrganizator(id);}
    public Thanhvien getMemberByID(Integer id){return activityRepository.getMemberByID(id);}
    public List<Thanhvien> getMemberList(Integer id){return activityRepository.getMemberList(id);}
    public List<Hoatdong> getListActivityByMyID(Integer myID) {return activityRepository.getListActivityByMyID(myID);}
    public List<Hoatdong> getActivityByMember(Integer id) {
        return activityRepository.getActivityByMember(id);
    }
    public List<Hoatdong> getActivityByHost(Integer id) {
        return activityRepository.getActivityByHost(id);
    }

    public Dangky getRegisterInfo(Integer IdAct) {
        return activityRepository.registerInfor(IdAct);
    }

    public List<Hoatdong> getActOccuredByName(String nameAct){
        return activityRepository.getActOccuredByName(nameAct);
    }

    @Override
    public List<Hoatdong> getActOfMemberByName(Integer idAcc, String nameAct) {
        return activityRepository.getActOfMemberByName(idAcc,nameAct);
    }

    public void addActivity(Integer maChuDe, String tenHD, String diaDiem, String thoiGianBD, String thoiGianKT,
                            String sotnvtt, String sotnvtd, String moTa, String anh, Integer userID) {
        try {
            Hoatdong hoatDong = new Hoatdong();
            hoatDong.setMaChuDe(topicRepository.getChudeByID(maChuDe));
            hoatDong.setTenHD(tenHD);
            hoatDong.setDiaDiem(diaDiem);
            hoatDong.setThoiGianBD(LocalDate.parse(thoiGianBD));
            hoatDong.setThoiGianKT(LocalDate.parse(thoiGianKT));
            hoatDong.setSoTNVToiThieu(Integer.parseInt(sotnvtt));
            hoatDong.setSoTNVToiDa(Integer.parseInt(sotnvtd));
            hoatDong.setMoTa(moTa);
            hoatDong.setTinhTrangHD((byte) 0);
            hoatDong.setTinhTrangDuyet((byte) 1);
            hoatDong.setAnh(anh.getBytes());
            activityRepository.save(hoatDong);
            Taikhoan taikhoan = accountService.getTaiKhoanByID(userID);
            Dangky dangky = new Dangky();
            dangky.setPhanQuyen(true);
            dangky.setMaHD(hoatDong);
            dangky.setTrangThai(true);
            dangky.setThoiGianDK(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            dangky.setMaTK(taikhoan);
            registerService.saveDK(dangky);


        } catch (Exception e) {
            // Handle exception appropriately (log or throw a custom exception)
            e.printStackTrace();
            throw new RuntimeException("Error adding activity: " + e.getMessage());
        }
    }

}
