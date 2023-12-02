package com.example.pbl04.controller;

import com.example.pbl04.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EvaluateController {
    private EvaluateService evaluateService;
    @Autowired
    public EvaluateController(EvaluateService evaluateService){
        this.evaluateService = evaluateService;
    }
//    @GetMapping("/danh-gia")
//    public List<Danhgia> getEvaluate(){
//       return evaluateService.getAllEvaluate();
//    }

}
