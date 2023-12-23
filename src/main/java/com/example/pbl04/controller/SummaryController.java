package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SummaryController {
    private final SummaryService summaryService;
    private final ActivityService activityService;
    private final SessionService sessionService;
    private final RegisterService registerService;
    private final EvaluationService evaluationService;
    public SummaryController(SummaryService summaryService, ActivityService activityService, RegisterService registerService, SessionService sessionService, EvaluationService evaluationService) {
        this.summaryService=summaryService;
        this.activityService=activityService;
        this.sessionService = sessionService;
        this.registerService = registerService;
        this.evaluationService = evaluationService;
    }
    @GetMapping("/trang-chu-tong-ket")
    public String showSummaryList(Model model, HttpSession session)
    {
        sessionService.createSessionModel(model, session);
        Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
        List<Hoatdong> myActivities = activityService.getListActivityByMyID(myaccount.getId());
        List<Tongket> summaryList = summaryService.getSummaryList();
        model.addAttribute("summaryList",summaryList);
        model.addAttribute("myActivities",myActivities);

        return "TrangChuTongKet";
    }
    @RequestMapping(value = "/Check-Summary")
    @ResponseBody
    public Map<String, Boolean> checkSummary(@RequestParam("maHD") Integer hoatdongId) {
        Map<String, Boolean> response = new HashMap<>();
        boolean summaryExists;
        Tongket summary = summaryService.getSummaryByID(hoatdongId);
        if(summary!=null)
             summaryExists=true;
        else  summaryExists =false;
        response.put("summaryExists", summaryExists);
        return response;
    }
    @RequestMapping(value ="/View-Summary")
    public String showDetailSummary(Model model,@RequestParam("id") Integer id, HttpSession session)
    {
        Tongket summary = summaryService.getSummaryByID(id);
        if (summary != null) {
            List<Tongket> summaryList = summaryService.getSummaryList();
            Taikhoan taikhoan = summaryService.getOrganizator(id);
            List<Thanhvien> memberList = summaryService.getMemberList(id);
            List<Anhtongket> imgSummaryList = summaryService.getimgSummaryList(summary.getId());
            Thanhvien member  = summaryService.getMemberByID(taikhoan.getId());
            List<Danhgia> evaluationOfAct = evaluationService.getEvaluationByIdAct(id);
            Dangky registerInfor = activityService.getRegisterInfo(id);
            model.addAttribute("summary", summary);
            model.addAttribute("memberList", memberList);
            model.addAttribute("member", member);
            model.addAttribute("imgSummaryList",imgSummaryList);
            model.addAttribute("summaryList",summaryList);
            model.addAttribute("evaluationOfAct",evaluationOfAct);
            model.addAttribute("registerInfor",registerInfor);
            sessionService.createSessionModel(model, session);
            return "TongKetHoatDong";
        } else {
//            sessionService.createSessionModel(model, session);
//            Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
//            Dangky checkDangky = registerService.getDangKyByHDTK(myaccount.getId(), id);
//            Taikhoan taikhoan =activityService.getOrganizator(id);
//            List<Thanhvien> thanhvienList =activityService.getMemberList(id);
//            Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
//            Hoatdong hoatdong = activityService.getActivityByID(id);
//            model.addAttribute("hoatdong",hoatdong);
//            model.addAttribute("taikhoan",taikhoan);
//            model.addAttribute("thanhvien",thanhvien);
//            model.addAttribute("thanhvienList",thanhvienList);
//            model.addAttribute("checkDangky",checkDangky);
//            model.addAttribute("showMessage", true);
            return "error";
        }
    }

}