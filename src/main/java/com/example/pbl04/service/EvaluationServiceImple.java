package com.example.pbl04.service;

import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.pbl04.entity.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImple implements EvaluationService {
    private final EvaluationRepository evaluationRepo;
    private final MemberService memberService;


    @Autowired
    public EvaluationServiceImple(EvaluationRepository evaluationRepo, MemberService memberService,ActivityService activityService){
        this.evaluationRepo = evaluationRepo;
        this.memberService = memberService;
    }

    @Override
    public List<Danhgia> getAllEvaluation() {
        return evaluationRepo.findAll();
    }
    @Override
    public List<Danhgia> getEvaluationByIdAct(Integer IdAct) {
        return evaluationRepo.getEvaluationByIdAct(IdAct);
    }

    @Override
    public List<Integer> getTotalRateOfMember() {
        List<Thanhvien> members = memberService.getAllMember();
        List<Integer> totalRates = new ArrayList<>();
        for(Thanhvien member:members){
            List<Integer> rates = evaluationRepo.rateOfMember(member.getId());
            int countRate = 0;
            if (rates != null && !rates.isEmpty()) {
                for (Integer rate : rates) {
                    countRate += rate;
                }
            }
            else{
                countRate = 0;
            }
            totalRates.add(countRate);
        }
        return totalRates;
    }

    @Override
    public List<Integer> countEvaluation(List<Hoatdong> ActList) {
        List<Integer> countEvaList = new ArrayList<>();
        for(Hoatdong act : ActList){
            countEvaList.add(evaluationRepo.countEvaluation(act.getId()));
        }
        return countEvaList;
    }

    @Override
    public Integer countEvaByIdAct(Integer IdAct) {
       return evaluationRepo.countEvaluation(IdAct);
    }


    @Override
    public List<List<Danhgia>> getEvaluationByIdActList(List<Hoatdong> activities) {
        List<List<Danhgia>> evaluations = new ArrayList<>();

        for (Hoatdong activity : activities) {
            List<Danhgia> evaluationList = evaluationRepo.getEvaluationByIdAct(activity.getId());
            evaluations.add(evaluationList);
        }

        return evaluations;
    }


    @Override
    public Danhgia getEvaluationByIDHDTK(Integer IdAct, Integer IdAcc) {
        return evaluationRepo.getEvaluationByIDHDTK(IdAct,IdAcc);
    }

    @Override
    public void evaluateActivity(Danhgia evaluation) {
        evaluationRepo.save(evaluation);
    }

    @Override
    @Transactional
    public void insertEvaluateAct(Integer maTK, Integer maHD, Integer diemTC, Boolean tieuChi1, Boolean tieuChi2, Boolean tieuChi3, String binhLuan, Instant thoiGianBL) {
        evaluationRepo.insertEvaluateAct(maTK,maHD,diemTC,tieuChi1,tieuChi2,tieuChi3,binhLuan,thoiGianBL);
    }

    @Override
    public List<Integer> getMembersScoreByAct(List<Thanhvien> members, Integer IdAct) {
        List<Integer> scores = new ArrayList<>();
        if(members != null){
            for (Thanhvien member : members) {
                Integer score = evaluationRepo.getMemberScoreByAct(member.getMaTK().getId(), IdAct);
                if (score == null) {
                    scores.add(0);
                } else {
                    scores.add(score);
                }
            }
        }
        return scores;
    }

    @Override
    public void evaluateMember(Danhgia evaluation) {
        evaluationRepo.save(evaluation);
    }

    @Override
    @Transactional
    public void insertEvaluateMemb(Integer maTK, Integer maHD, Integer diemTNV) {
        evaluationRepo.insertEvaluateMemb(maTK,maHD,diemTNV);
    }
    public List<Integer> getPointOfMember(List<Taikhoan> thanhvienList)
    {
        List<Integer> pointList = new ArrayList<>();
        for(Taikhoan thanhvien : thanhvienList){
            Integer point = evaluationRepo.sumPointByIDTK(thanhvien.getId());
            if(point==null){
                pointList.add(0);
            }else{
                pointList.add(point);
            }

        }
        return  pointList;
    }

}
