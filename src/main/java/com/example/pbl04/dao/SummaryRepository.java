package com.example.pbl04.dao;

import com.example.pbl04.entity.Anhtongket;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.entity.Tongket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Tongket,Integer> {
    @Query("SELECT tk from Tongket tk where tk.id= :id")
    Tongket getSummaryByID(Integer id);

    @Query("SELECT d.maTK from Dangky d,Hoatdong h where d.phanQuyen=true and d.maHD = h and h.id= :id")
    Taikhoan getOrganizator(Integer id);
    @Query("SELECT tt from Taikhoan tk , Thanhvien tt where tk = tt.maTK and tk.id=:id")
    Thanhvien getMemberByID(Integer id);
    @Query("select tv from Taikhoan tk,Thanhvien tv, Dangky dk where dk.maHD.id =:id and dk.maTK =tk and tk= tv.maTK and dk.phanQuyen=false")
    List<Thanhvien> getMemberList(Integer id);
    @Query("select a from Anhtongket a where a.maTongKet=:id")
    List<Anhtongket> getimgSummaryList(Integer id);
}