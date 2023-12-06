package com.example.pbl04.service;

import com.example.pbl04.entity.Danhgia;

import java.util.List;

public interface EvaluationService {
    public List<Danhgia> getAllEvaluation();

    List<Integer> getTotalRateOfMember();
}
