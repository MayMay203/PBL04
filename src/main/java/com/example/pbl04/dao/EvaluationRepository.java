package com.example.pbl04.dao;

import com.example.pbl04.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Danhgia,Integer> {
    @Query("SELECT h.diemTNV FROM Danhgia h WHERE h.id = :id")
    List<Integer> rateOfMember(int id);

    @Query("select count(*) from Danhgia dg where dg.maHD.id = :IdAct and (dg.binhLuan is not null or dg.diemTC is not null)")
    Integer countEvaluation(Integer IdAct);

    @Query("select dg from Danhgia dg where dg.maHD.id = :IdAct and (dg.binhLuan IS NOT NULL or dg.diemTC IS NOT NULL) order by dg.thoiGianBL desc")
    List<Danhgia> getEvaluationByIdAct(Integer IdAct);

    @Query("select dg from Danhgia  dg where dg.maHD.id = :IdAct and dg.maTK.id = :IdAcc")
    Danhgia getEvaluationByIDHDTK(Integer IdAct,Integer IdAcc);

    @Query("select dg.diemTNV from Danhgia  dg where dg.maHD.id = :IdAct and dg.maTK.id = :IdAcc")
    Integer getRateEvaluationByIDHDTK(Integer IdAct,Integer IdAcc);

    @Modifying
    @Query("update Danhgia dg set dg.diemTC=:diemTC,dg.tieuChi1=:tieuChi1,dg.tieuChi2 = :tieuChi2,dg.tieuChi3=:tieuChi3,dg.binhLuan=:binhLuan,dg.thoiGianBL=:thoiGianBL where dg.maTK.id=:maTK and dg.maHD.id=:maHD")
    void insertEvaluateAct(Integer maTK, Integer maHD, Integer diemTC, Boolean tieuChi1, Boolean tieuChi2, Boolean tieuChi3, String binhLuan, Instant thoiGianBL);

    @Query("select dg.diemTNV from Danhgia dg where dg.maTK.id = :maTK and dg.maHD.id = :maHD")
    Integer getMemberScoreByAct(int maTK,int maHD);

    @Modifying
    @Query("update Danhgia dg set dg.diemTNV=:diemTNV where dg.maTK.id=:maTK and dg.maHD.id=:maHD")
    void insertEvaluateMemb(Integer maTK, Integer maHD, Integer diemTNV);
    @Query("select sum(dg.diemTNV) from Danhgia dg where dg.maTK.id=:idtk")
    Integer sumPointByIDTK(Integer idtk);
}
