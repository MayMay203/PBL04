package com.example.pbl04.service;

import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Danhgia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class EvaluationServiceImple implements EvaluationService {
    private final EvaluationRepository evaluationRepo;

    @Autowired
    public EvaluationServiceImple(EvaluationRepository evaluationRepo){
        this.evaluationRepo = evaluationRepo;
    }
    @Override
    public List<Danhgia> getAllEvaluation() {
        return evaluationRepo.findAll();
    }




}
