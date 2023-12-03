package com.example.pbl04.service;

import com.example.pbl04.entity.Danhgia;

import java.lang.reflect.Member;
import java.util.List;

public interface EvaluationService {

     List<Danhgia> getAllEvaluate();

     List<Integer> getTotalRateOfMember();
}
