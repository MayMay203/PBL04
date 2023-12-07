package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.SummaryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SummaryController {
    private final SummaryService summaryService;
    private final ActivityService activityService;
    public SummaryController(SummaryService summaryService,ActivityService activityService) {
        this.summaryService=summaryService;
        this.activityService=activityService;
    }
    @GetMapping("/trang-chu-tong-ket")
    public String showSummaryList(Model model, HttpSession session)
    {
        Integer myID=4;
        List<Hoatdong> myActivities = activityService.getActivityByMyID(myID);
        model.addAttribute("Anh",new Anh());
        List<Tongket> summaryList = summaryService.getSummaryList();
        model.addAttribute("summaryList",summaryList);
        model.addAttribute("myActivities",myActivities);
        Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
        if (account == null) {
            model.addAttribute("account", new Taikhoan());
            model.addAttribute("isLogin",false);
        } else {
            model.addAttribute("account", account);
            model.addAttribute("isLogin",true);
        }
        return "TrangChuTongKet";
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
            model.addAttribute("Anh",new Anh());
            model.addAttribute("summary", summary);
            model.addAttribute("memberList", memberList);
            model.addAttribute("member", member);
            model.addAttribute("imgSummaryList",imgSummaryList);
            model.addAttribute("summaryList",summaryList);
            Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
            if (account == null) {
                model.addAttribute("account", new Taikhoan());
                model.addAttribute("isLogin",false);
            } else {
                model.addAttribute("account", account);
                model.addAttribute("isLogin",true);
            }
            return "TongKetHoatDong";
        } else {
            return "errorPage";
        }
    }

}