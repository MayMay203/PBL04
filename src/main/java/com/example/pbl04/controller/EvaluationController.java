package com.example.pbl04.controller;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvaluationController {
    private final EvaluationService evaluateService;
    private final ActivityService activityService;
    @Autowired
    public EvaluationController(EvaluationService evaluateService,ActivityService activityService){
        this.evaluateService = evaluateService;
        this.activityService = activityService;
    }

    @GetMapping("/danh-gia")
    public List<Danhgia> getEvaluate(){
        List<Danhgia> evaluateList = evaluateService.getAllEvaluate();
        return evaluateList;
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome(Model model){
            List<Hoatdong> activityList = activityService.getActivityOccured();
            List<Danhgia> evaluationList =  evaluateService.getAllEvaluate();
            model.addAttribute("evaluationList",evaluationList);
            model.addAttribute("activityList",activityList);
            System.out.println(activityList.size());
            return "TrangChuDanhGia";
    }
}
