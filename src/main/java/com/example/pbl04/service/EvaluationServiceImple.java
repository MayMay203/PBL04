package com.example.pbl04.service;

import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImple implements EvaluationService {
    private final EvaluationRepository evaluateRepo;
    private final MemberService memberService;

    @Autowired
    private EvaluationServiceImple(EvaluationRepository evaluateRepo, MemberService memberService){
        this.evaluateRepo = evaluateRepo;
        this.memberService = memberService;
    }
    @Override
    public List<Danhgia> getAllEvaluate() {
        return evaluateRepo.findAll();
    }

    @Override
    public List<Integer> getTotalRateOfMember() {
        List<Thanhvien> members = memberService.getAllMember();
        List<Integer> totalRates = new ArrayList<>();
        for(Thanhvien member:members){
            List<Integer> rates = evaluateRepo.rateOfMember(member.getId());
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


}
