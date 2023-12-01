package com.example.pbl04.service;

import com.example.pbl04.dao.EvaluateRepository;
import com.example.pbl04.entity.Danhgia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImple implements EvaluateService {
    private final EvaluateRepository evaluateRepo;

    @Autowired
    public EvaluateServiceImple(EvaluateRepository evaluateRepo){
        this.evaluateRepo = evaluateRepo;
    }
    @Override
    public List<Danhgia> getAllEvaluate() {
        return evaluateRepo.findAll();
    }
}
