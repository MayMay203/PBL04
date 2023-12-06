package com.example.pbl04.service;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;

import java.lang.reflect.Member;
import java.util.List;

public interface EvaluationService {
    public List<Danhgia> getAllEvaluation();

    List<Integer> getTotalRateOfMember();

    List<Integer> countEvaluation(List<Hoatdong> actList);

    Integer countEvaByIDHD(Integer IDHD);

}
