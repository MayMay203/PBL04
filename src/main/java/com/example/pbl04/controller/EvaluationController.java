package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public String getEvaluationHome(Model model, HttpSession session){
        List<Hoatdong> activityList = activityService.getActivityOccured();
        List<Danhgia> evaluationList =  evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        model.addAttribute("activityList",activityList);
        List<Integer> countEvaList = evaluationService.countEvaluation(activityList);
        model.addAttribute("countEvaList",countEvaList);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
        if (account == null) {
            model.addAttribute("account", new Taikhoan());
            model.addAttribute("isLogin",false);
        } else {
            model.addAttribute("account", account);
            model.addAttribute("isLogin",true);
        }
            return "TrangChuDanhGia";
    }

    @GetMapping("/danh-gia")
    public String getAllEvaluation(Model model, @RequestParam("id") Integer id, HttpSession session){
        List<Danhgia> evaluationList = evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        List<Hoatdong> actListOfMember = activityService.getActivityByMember(id);
        model.addAttribute("actListOfMember",actListOfMember);
        List<Hoatdong> actListOfHost = activityService.getActivityByHost(id);
        model.addAttribute("actListOfHost",actListOfHost);
        System.out.println(actListOfMember.size());
        System.out.println(actListOfHost.size());
        // Kiểm tra xem người dùng đã đăng nhập chưa
        Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
        if (account == null) {
            model.addAttribute("account", new Taikhoan());
            model.addAttribute("isLogin",false);
        } else {
            model.addAttribute("account", account);
            model.addAttribute("isLogin",true);
        }
        return "DanhGia";
    }

    @GetMapping("/hoat-dong/xem-chi-tiet/{IdAct}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> Activity(@PathVariable(name = "IdAct", required = false) Integer IdAct, Model model) {
        Map<String, Object> responseData = new HashMap<>();
        Hoatdong activity = activityService.getActivityByID(IdAct);
        int numberEvaluation = evaluationService.countEvaByIdAct(IdAct);
        List<Danhgia> evaluationOfAct = evaluationService.getEvaluationByIdAct(IdAct);
        Dangky registerInfor = activityService.getRegisterInfo(IdAct);
//        Instant timeRegister = activityService.getTimeResgister(IdAct);
//        System.out.println(evaluationOfAct);
        responseData.put("activity", activity);
        responseData.put("numberEvaluation", numberEvaluation);
        responseData.put("evaluationOfAct",evaluationOfAct);
        responseData.put("registerInfor",registerInfor);
        return ResponseEntity.ok(responseData);
    }
}
