package com.example.pbl04.service;

import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImple implements EvaluationService {
    private final EvaluationRepository evaluationRepo;
    private final MemberService memberService;

    @Autowired
    public EvaluationServiceImple(EvaluationRepository evaluationRepo, MemberService memberService){
        this.evaluationRepo = evaluationRepo;
        this.memberService = memberService;
    }
    @Override
    public List<Danhgia> getAllEvaluation() {
        return evaluationRepo.findAll();
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
    public Integer countEvaByIDHD(Integer IDHD) {
       return evaluationRepo.countEvaluation(IDHD);
    }


}
