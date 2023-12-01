package com.example.pbl04.controller;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.service.EvaluateService;
import com.example.pbl04.service.EvaluateServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvaluateController {
    private EvaluateService evaluateService;
    @Autowired
    public EvaluateController(EvaluateService evaluateService){
        this.evaluateService = evaluateService;
    }
    @GetMapping("/danh-gia")
    public List<Danhgia> getEvaluate(){
        List<Danhgia> evaluateList = evaluateService.getAllEvaluate();
        return evaluateList;
    }

}
