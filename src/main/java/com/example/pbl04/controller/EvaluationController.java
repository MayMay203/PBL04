package com.example.pbl04.controller;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvaluationController {
    private final EvaluationService evaluateService;
    @Autowired
    public EvaluationController(EvaluationService evaluateService){
        this.evaluateService = evaluateService;
    }

    @GetMapping("/danh-gia")
    public List<Danhgia> getEvaluate(){
        List<Danhgia> evaluateList = evaluateService.getAllEvaluate();
        return evaluateList;
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome(Model model){
            List<Danhgia> evaluationList =  evaluateService.getAllEvaluate();
            model.addAttribute("evaluationList",evaluationList);
            return "TrangChuDanhGia";
    }
}
