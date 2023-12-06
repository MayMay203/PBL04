package com.example.pbl04.service;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
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
    public Dangky getDateRegis(Integer mahd){return activityRepository.getDateRegis();}
    public Integer getParticipants() {return activityRepository.getParticipants();}
    public Taikhoan getOrganizator(Integer id){ return activityRepository.getOrganizator(id);}
    public Thanhvien getMemberByID(Integer id){return activityRepository.getMemberByID(id);}
    public List<Thanhvien> getMemberList(Integer id){return activityRepository.getMemberList(id);}
    public List<Hoatdong> getActivityByMember(Integer id) {
        return activityRepository.getActivityByMember(id);
    }
    public List<Hoatdong> getActivityByHost(Integer id) {
        return activityRepository.getActivityByHost(id);
    }

    public Dangky getRegisterInfo(Integer IdAct) {
        return activityRepository.registerInfor(IdAct);
    }

//    @Override
//    public Instant getTimeResgister(Integer IdAct) {
//        return activityRepository.getTimeRegister(IdAct);
//    }
}
