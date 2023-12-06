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
    private final EvaluationService evaluationService;
    private final ActivityService activityService;
    @Autowired
    public EvaluationController(EvaluationService evaluationService,ActivityService activityService){
        this.evaluationService = evaluationService;
        this.activityService = activityService;
    }

    @GetMapping("/danh-gia")
    public String getAllEvaluation(Model model){
        List<Danhgia> evaluationList = evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        return "DanhGia";
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome(Model model){
            List<Hoatdong> activityList = activityService.getActivityOccured();
            List<Danhgia> evaluationList =  evaluationService.getAllEvaluation();
            model.addAttribute("evaluationList",evaluationList);
            model.addAttribute("activityList",activityList);
            System.out.println(activityList.size());
//            return "TrangChuDanhGia";
        return "TrangChuDanhGia";
    }
}
