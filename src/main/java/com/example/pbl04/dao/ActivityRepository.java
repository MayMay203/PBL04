package com.example.pbl04.dao;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Hoatdong,Integer> {
    @Query("SELECT h FROM Hoatdong h")
    List<Hoatdong> getALlActivity();
    @Query("SELECT h FROM Hoatdong h WHERE h.id = :id")
    Hoatdong findActivityByID(int id);

     @Query("SELECT h from Hoatdong h where h.tinhTrangHD = 3")
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
    @Query("select tv from Taikhoan tk,Thanhvien tv, Dangky dk where dk.maHD.id =:id and dk.maTK =tk and tk= tv.maTK and dk.phanQuyen=false")
    List<Thanhvien> getMemberList(Integer id);
   //Lấy List hoạt động đã tham gia bằng id tài khoản
   @Query("select hd from Hoatdong hd, Dangky dk where dk.maTK.id= :myID and hd.id = dk.maHD.id and hd.tinhTrangHD=3 and dk.phanQuyen=false")
   List<Hoatdong> getListActivityByMyID(Integer myID);

    //Danh gia
    @Query("select h from Hoatdong h,Dangky dk where dk.phanQuyen=false and h.id = dk.maHD.id and dk.maTK.id=:id and h.tinhTrangHD=3")
    List<Hoatdong> getActivityByMember(Integer id);

    @Query("select h from Hoatdong h, Dangky dk where dk.phanQuyen=true and h.id = dk.maHD.id and dk.maTK.id = :id and h.tinhTrangHD=3")
    List<Hoatdong> getActivityByHost(Integer id);

    @Query("select dk from Dangky dk where dk.maHD.id = :IdAct and dk.phanQuyen=true")
    Dangky registerInfor(Integer IdAct);

    @Query("select hd from Hoatdong hd where hd.tenhd like %:nameAct% and hd.tinhTrangHD=3")
    List<Hoatdong> getActOccuredByName(String nameAct);

    @Query("select h from Hoatdong h,Dangky dk where dk.phanQuyen=false and h.id = dk.maHD.id and dk.maTK.id=:idAcc and h.tinhTrangHD=3 and h.tenhd like %:nameAct%")
    List<Hoatdong> getActOfMemberByName(Integer idAcc, String nameAct);

    @Query("select hd from Hoatdong hd where hd.id =:id")
    Hoatdong getActivityByID(Integer id);
}
