package com.example.pbl04.service;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.dao.TopicRepository;
import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public List<Hoatdong> getAllActiviyPost() {return activityRepository.getAllActiviyPost();}
    public List<Hoatdong> getMyActivityConfirm(Integer maTK) {return activityRepository.getMyActivityConfirm(maTK);}
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
                            String sotnvtt, String sotnvtd, String moTa
            , String anh
            ,Integer maTK) {
        try {
            Hoatdong hoatDong = new Hoatdong();
            hoatDong.setMaChuDe(topicRepository.getChudeByID(maChuDe));
            hoatDong.setTenhd(tenHD);
            hoatDong.setDiaDiem(diaDiem);
            hoatDong.setThoiGianBD(LocalDate.parse(thoiGianBD));
            hoatDong.setThoiGianKT(LocalDate.parse(thoiGianKT));
            hoatDong.setSoTNVToiThieu(Integer.parseInt(sotnvtt));
            hoatDong.setSoTNVToiDa(Integer.parseInt(sotnvtd));
            hoatDong.setMoTa(moTa);
            hoatDong.setTinhTrangHD((byte) 0);
            hoatDong.setTinhTrangDuyet((byte) 1);
            hoatDong.setAnh(anh);
            activityRepository.save(hoatDong);
            Taikhoan taikhoan = accountService.getTaiKhoanByID(maTK);
            Dangky dangky = new Dangky();
            dangky.setPhanQuyen(true);
            dangky.setMaHD(hoatDong);
            dangky.setTrangThai(true);
//            dangky.setThoiGianDK(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            dangky.setThoiGianDK(Instant.now());
            dangky.setMaTK(taikhoan);
            registerService.saveDK(dangky);
            System.out.println("chạy service");

        } catch (Exception e) {
            // Handle exception appropriately (log or throw a custom exception)
            e.printStackTrace();
            throw new RuntimeException("Error adding activity: " + e.getMessage());
        }
    }

    @Override
    public void addMyActivity(Hoatdong hoatdong) {
        activityRepository.save(hoatdong);
    }

    public List<Hoatdong> getAllMyPostNeedConfirm(Integer maTK) {return activityRepository.getAllMyPostNeedConfirm(maTK);}
    public List<Dangky> getListActivityParticipate(Integer maTK) {return  activityRepository.getListActivityParticipate(maTK);}
    public List<Hoatdong> getMyActivityHappeningNeedMember(Integer maTK) {return activityRepository.getMyActivityHappeningNeedMember(maTK);}
    public void confirmActivityStage0(Integer maHD) { activityRepository.confirmActivityStage0(maHD);}
    public void confirmActivityStage1(Integer maHD) { activityRepository.confirmActivityStage1(maHD);}
    public List<Hoatdong> getAllMyActivityNeedConfirm(Integer maTK) {return activityRepository.getAllMyActivityNeedConfirm(maTK);}
//    public List<Hoatdong> getMyActivityNeedMember(Integer maTK) {return activityRepository.getMyActivityNeedMember(maTK);}
    public List<Integer> countConfirm(List<Hoatdong> ActList) {
        List<Integer> countConfirms = new ArrayList<>();
        for (Hoatdong act : ActList) {
            countConfirms.add(activityRepository.countConfirm(act.getId()));
        }
        return countConfirms;
    }
    public List<Integer> countConfirmed(List<Hoatdong> ActList) {
        List<Integer> countConfirms = new ArrayList<>();
        for (Hoatdong act : ActList) {
            countConfirms.add(activityRepository.countConfirmed(act.getId()));
        }
        return countConfirms;
    }
    public void CancelActivity(Integer maHD, String txtHuy)
    {
        activityRepository.CancelActivity(maHD,txtHuy);
    }
    public List<Hoatdong> getListCancel(){ return activityRepository.getListCancel();}
    public List<Hoatdong> getListCancelByOwner(Integer maTK) {return activityRepository.getListCancelByOwner(maTK);}
    public boolean CheckActivity(Integer maHD) {
       Hoatdong hoatdong = activityRepository.getActivityByID(maHD);
       if (hoatdong.getTinhTrangDuyet()==0 || hoatdong.getTinhTrangHD()==0 ||
       hoatdong.getTinhTrangDuyet()==-1 || hoatdong.getTinhTrangHD()==-1)
           return false;
       else return true;
    }
    public Integer countParticipantsByIDHD(Integer maHD){ return  activityRepository.countParticipantsByIDHD(maHD);}

    @Override
    public List<Integer> countActByLocation(List<String> locationList) {
       List<Integer> countList = new ArrayList<>();
       for(String location:locationList){
           countList.add(activityRepository.countActByLocation(location));
       }
       return countList;
    }
}
