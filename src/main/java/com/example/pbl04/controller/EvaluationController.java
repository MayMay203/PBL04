package com.example.pbl04.controller;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final ActivityService activityService;
    @Autowired
    public EvaluationController(EvaluationService evaluationService,ActivityService activityService){
        this.evaluationService = evaluationService;
        this.activityService = activityService;
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome(Model model){
            List<Hoatdong> activityList = activityService.getActivityOccured();
            List<Danhgia> evaluationList =  evaluationService.getAllEvaluation();
            model.addAttribute("evaluationList",evaluationList);
            model.addAttribute("activityList",activityList);
            List<Integer> countEvaList = evaluationService.countEvaluation(activityList);
            model.addAttribute("countEvaList",countEvaList);
            return "TrangChuDanhGia";
    }

    @GetMapping("/danh-gia")
    public String getAllEvaluation(Model model, @RequestParam("id") Integer id){
        List<Danhgia> evaluationList = evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        List<Hoatdong> actListOfMember = activityService.getActivityByMember(id);
        model.addAttribute("actListOfMember",actListOfMember);
        List<Hoatdong> actListOfHost = activityService.getActivityByHost(id);
        model.addAttribute("actListOfHost",actListOfHost);
        System.out.println(actListOfMember.size());
        System.out.println(actListOfHost.size());
        return "DanhGia";
    }

    @GetMapping("/hoat-dong/xem-chi-tiet/{idhd}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> Activity(@PathVariable(name = "idhd", required = false) Integer idhd, Model model) {
        Map<String, Object> responseData = new HashMap<>();
        Hoatdong activity = activityService.getActivityByID(idhd);
        int numberEvaluation = evaluationService.countEvaByIDHD(idhd);
        responseData.put("activity", activity);
        responseData.put("numberEvaluation", numberEvaluation);
        return ResponseEntity.ok(responseData);
    }
}
