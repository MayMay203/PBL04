package com.example.pbl04.service;

import java.time.Instant;
import java.util.List;
import com.example.pbl04.entity.*;

public interface EvaluationService {
    public List<Danhgia> getAllEvaluation();
    public List<Danhgia> getEvaluationByIdAct(Integer IdAct);

    List<Integer> getTotalRateOfMember();

    List<Integer> countEvaluation(List<Hoatdong> actList);

    Integer countEvaByIdAct(Integer IdAct);

    List<List<Danhgia>> getEvaluationByIdActList(List<Hoatdong> activities);



    Danhgia getEvaluationByIDHDTK(Integer IdAct,Integer IdAcc);
    void evaluateActivity(Danhgia evaluation);
    void insertEvaluateAct(Integer maTK, Integer maHD, Integer diemTC, Boolean tieuChi1, Boolean tieuChi2, Boolean tieuChi3, String binhLuan, Instant thoiGianBL);

    List<Integer>getMembersScoreByAct(List<Thanhvien> members,Integer IdAct);

    void evaluateMember(Danhgia evaluation);
    void insertEvaluateMemb(Integer maTK, Integer maHD, Integer diemTNV);
}
