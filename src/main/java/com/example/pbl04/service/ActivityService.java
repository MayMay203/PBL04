package com.example.pbl04.service;


import com.example.pbl04.entity.*;


import java.util.List;

public interface ActivityService {
    List<Hoatdong> getActivityToRegis();
    List<Hoatdong> getAllActivity();
    Hoatdong getActivityByID(Integer id);
    List<Hoatdong> getActivityOccured();
    List<Dangky> getActivityUpcomming();
    List<Hoatdong> getActivityHappening();
    List<Hoatdong> getAllActiviyPost();
    //List<Hoatdong> getMyActivityConfirm(Integer id);
    Dangky getDateRegis(Integer mahd);
    Integer getNumActivity();
    Integer getParticipants();
    Taikhoan getOrganizator(Integer id);
    Thanhvien getMemberByID(Integer id);
    List<Thanhvien> getMemberList(Integer id);
//    List<Hoatdong> getActivityByMyID(Integer myID);
    List<Taikhoan> getAccountList(Integer id);
    List<Hoatdong> getListActivityByMyID(Integer myID);
    //Danh gia
    List<Hoatdong> getActivityByMember(Integer id);
    List<Hoatdong> getActivityByHost(Integer id);

    Dangky getRegisterInfo(Integer IdAct);
//    Instant getTimeResgister(Integer IdAct);

    List<Hoatdong> getActOccuredByName(String nameAct);

    List<Hoatdong> getActOfMemberByName(Integer idAcc,String nameAct);

    void addActivity(Hoatdong hoatdong, Dangky dangky);
    List<Hoatdong> getAllMyPostNeedConfirm(Integer maTK);
    List<Dangky> getListActivityParticipate(Integer maTK);
    List<Hoatdong> getMyActivityHappeningNeedMember(Integer maTK);
    void confirmActivityStage0(Integer maHD);
    void confirmActivityStage1(Integer maHD);
    List<Hoatdong> getAllMyActivityNeedConfirm(Integer maTK);
//    List<Hoatdong> getMyActivityNeedMember(Integer maTK);
    List<Integer> countConfirm(List<Hoatdong> actList);
    List<Integer> countConfirmed(List<Hoatdong> actList);
    void CancelActivity(Integer maHD, String txtHuy);
//void addActivity(Integer maChuDe, String tenHD, String diaDiem, String thoiGianBD, String thoiGianKT,
//                 String sotnvtt, String sotnvtd, String moTa,Integer maTK);
    void addMyActivity(Hoatdong hoatdong);
     List<Hoatdong> getListCancel();
     List<Hoatdong> getListCancelByOwner(Integer maTK);
     boolean CheckActivity(Integer maHD);
     Integer countParticipantsByIDHD(Integer maHD);
     List<Integer> countActByLocation(List<String> locationList);
     List<Hoatdong> getActByLocation(String location);
     List<Hoatdong> getAllActivityNotOccured();
     List<Hoatdong> getActivityByNameAct(String nameAct);

}