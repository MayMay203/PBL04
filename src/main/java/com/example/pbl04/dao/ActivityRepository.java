package com.example.pbl04.dao;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Hoatdong,Integer> {
    @Query("SELECT h FROM Hoatdong h")
    List<Hoatdong> getALlActivity();
    @Query("SELECT h FROM Hoatdong h WHERE h.id = :id")
    Hoatdong findActivityByID(int id);

     @Query("SELECT h from Hoatdong h where h.tinhTrangHD = 2 and h.tinhTrangDuyet=2")
    List<Hoatdong> getActivityOccured();
     @Query("select dk from Hoatdong h,Dangky dk where h.tinhTrangHD = 1 and h = dk.maHD and dk.phanQuyen=true")
     List<Dangky> getActivityUpcomming();
     @Query("select dk from Hoatdong h , Dangky dk where h= dk.maHD and dk.phanQuyen=true")
     Dangky getDateRegis();
    @Query("select h from Hoatdong h where h.tinhTrangHD = 2")
     List<Hoatdong> getActivityHappening();
     @Query("select count(*) from Hoatdong")
     Integer getNumActivity();
     @Query("SELECT count(*) from Dangky d where d.trangThai=true and d.phanQuyen=false")
     Integer getParticipants();
     @Query("SELECT d.maTK from Dangky d,Hoatdong h where d.phanQuyen=true and d.maHD = h and h.id= :id")
     Taikhoan getOrganizator(Integer id);
    @Query("SELECT tt from Taikhoan tk , Thanhvien tt where tk = tt.maTK and tk.id=:id")
    Thanhvien getMemberByID(Integer id);
    @Query("select tv from Taikhoan tk,Thanhvien tv, Dangky dk where dk.maHD.id =:id and dk.maTK =tk and tk= tv.maTK and dk.phanQuyen=false and dk.trangThai=true")
    List<Thanhvien> getMemberList(Integer id);
   //Lấy List hoạt động đã tham gia bằng id tài khoản
   @Query("select hd from Hoatdong hd, Dangky dk where dk.maTK.id= :myID and hd.id = dk.maHD.id and hd.tinhTrangHD=3 and dk.phanQuyen=false")
   List<Hoatdong> getListActivityByMyID(Integer myID);

    //Danh gia
    @Query("select h from Hoatdong h,Dangky dk where dk.phanQuyen=false and h.id = dk.maHD.id and dk.maTK.id=:id and h.tinhTrangHD=2 and h.tinhTrangDuyet=2")
    List<Hoatdong> getActivityByMember(Integer id);

    @Query("select h from Hoatdong h, Dangky dk where dk.phanQuyen=true and h.id = dk.maHD.id and dk.maTK.id = :id and h.tinhTrangHD=2 and h.tinhTrangDuyet=2")
    List<Hoatdong> getActivityByHost(Integer id);

    @Query("select dk from Dangky dk where dk.maHD.id = :IdAct and dk.phanQuyen=true")
    Dangky registerInfor(Integer IdAct);

    @Query("select hd from Hoatdong hd where hd.tenhd like %:nameAct% and hd.tinhTrangHD=2 and hd.tinhTrangDuyet=2")
    List<Hoatdong> getActOccuredByName(String nameAct);

    @Query("select h from Hoatdong h,Dangky dk where dk.phanQuyen=false and h.id = dk.maHD.id and dk.maTK.id=:idAcc and h.tinhTrangHD=2 and h.tinhTrangDuyet = 2 and h.tenhd like %:nameAct%")
    List<Hoatdong> getActOfMemberByName(Integer idAcc, String nameAct);

    @Query("select hd from Hoatdong hd where hd.id =:id")
    Hoatdong getActivityByID(Integer id);
    // Get hoạt động cho phần xét duyệt
    @Query("select hd from Hoatdong hd where hd.tinhTrangDuyet=0") // Hoạt động chờ xét duyệt
    List<Hoatdong> getAllActiviyPost();
    @Query("select hd from Dangky dk, Hoatdong hd where hd.id=dk.maHD.id and dk.phanQuyen=true  and dk.maTK.id=:myID ") ////Bao gồm hoạt động chưa xét duyệt, đã xét duyệt, đã huy.....
    List<Hoatdong> getAllMyPostNeedConfirm(Integer myID);
    @Query("select dk from Dangky dk, Hoatdong hd where hd.id=dk.maHD.id  and dk.maTK.id=:myID and dk.phanQuyen=false")
    List<Dangky> getListActivityParticipate(Integer myID);
    @Query("select hd from Dangky dk, Hoatdong hd where dk.maHD.id= hd.id and dk.maTK.id =:maTK") //Hoạt động chờ xét duệt được phân loại theo maTK
    List<Hoatdong> getMyActivityConfirm(Integer maTK);
    @Query("select hd from Hoatdong hd, Dangky dk where hd.id= dk.maHD.id and dk.maTK.id=:maTK and dk.phanQuyen=true and hd.tinhTrangHD=2")
    List<Hoatdong> getMyActivityHappeningNeedMember(Integer maTK);
    @Transactional
    @Modifying
    @Query("update Hoatdong hd set hd.tinhTrangDuyet=1 , hd.tinhTrangHD=1 where hd.id=:maHD")
    void confirmActivityStage0(Integer maHD);

    @Transactional
    @Modifying
    @Query("update Hoatdong hd set hd.tinhTrangDuyet=2 , hd.tinhTrangHD=1 where hd.id=:maHD")
    void confirmActivityStage1(Integer maHD);

    @Query("select hd from Hoatdong hd,Dangky dk where dk.maHD.id=hd.id and dk.maTK.id=:maTK and dk.phanQuyen=true and hd.tinhTrangDuyet=1 and hd.tinhTrangHD=1")
    List<Hoatdong> getAllMyActivityNeedConfirm(Integer maTK);
    @Query("select hd from Hoatdong hd,Dangky dk where dk.maHD.id=hd.id and dk.phanQuyen=true and dk.id=:maTK and hd.tinhTrangHD=1 and hd.tinhTrangDuyet=1")
    List<Hoatdong> getMyActivityNeedMember(Integer maTK);
    @Query("select count(*) from Hoatdong hd, Dangky dk where hd.id = :IdAct and dk.maHD.id=hd.id and dk.trangThai=false and dk.phanQuyen=false ")
    Integer countConfirm(Integer IdAct);
    @Query("select count(*) from Hoatdong hd, Dangky dk where hd.id = :IdAct and dk.maHD.id=hd.id and dk.trangThai=true and dk.phanQuyen=false ")
    Integer countConfirmed(Integer IdAct);
    @Transactional
    @Modifying
    @Query("update Hoatdong hd set hd.liDoHuy=:txtHuy , hd.tinhTrangDuyet=-1, hd.tinhTrangHD=-1 where hd.id=:maHD")
    void CancelActivity(Integer maHD, String txtHuy);
    @Query("select hd from Hoatdong hd where hd.tinhTrangHD=-1 and hd.tinhTrangDuyet=-1")
    List<Hoatdong> getListCancel();
    @Query("select hd from Hoatdong hd,Dangky dk where hd.id = dk.maHD.id and dk.maTK.id=:maTK and dk.phanQuyen=true and hd.tinhTrangHD=-1 and hd.tinhTrangDuyet=-1")
    List<Hoatdong> getListCancelByOwner(Integer maTK);
    @Query("select count(*) from Dangky dk, Hoatdong hd where hd.id= dk.maHD.id and hd.id =:maHD and dk.phanQuyen=false and dk.trangThai=true")
    Integer countParticipantsByIDHD(Integer maHD);

    @Query("select count(*) from Hoatdong hd where hd.tinhTrangDuyet = 2 and hd.tinhTrangHD=2 and hd.diaDiem like %:location%")
    Integer countActByLocation(String location);

    @Query("select hd from Hoatdong hd where hd.diaDiem = :location and hd.tinhTrangHD=2 and hd.tinhTrangDuyet=2")
    List<Hoatdong> getActByLocation(String location);

}
