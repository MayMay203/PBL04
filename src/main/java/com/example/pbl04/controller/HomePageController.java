package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final MemberService memberService;
    private final EvaluationService evaluationService;
    private final ActivityService activityService;
    private final AboutUsService aboutUsService;
    private final SummaryService summaryService;
    @Autowired
    public HomePageController(MemberService memberService, EvaluationService evaluationService, ActivityService activityService, AboutUsService aboutUsService, SummaryService summaryService){
        this.memberService = memberService;
        this.evaluationService = evaluationService;
        this.activityService = activityService;
        this.aboutUsService = aboutUsService;
        this.summaryService = summaryService;
    }
//    @GetMapping("/trang-chu")
//    public String showMember(Model model){
//        List<Thanhvien> memberList = memberService.getAllMember();
//        model.addAttribute("memberList", memberList);
//        return "TrangChu";
//    }
    @GetMapping("/trang-chu")
    public String show(Model model)
    {
        List<Thanhvien> memberList = memberService.getAllMember();
        model.addAttribute("memberList", memberList);

        List<Integer> totalRates = evaluationService.getTotalRateOfMember();
        model.addAttribute(("totalRates"),totalRates);


        List<Hoatdong> activitiesList = activityService.getAllActivity();
        model.addAttribute("activitiesList",activitiesList);

        List<Vetrangweb> aboutUs = aboutUsService.getAllAboutUs();
        model.addAttribute("aboutUs",aboutUs);

        List<Tongket> summaryList = summaryService.getSummaryList();
        model.addAttribute("summaryList",summaryList);

        model.addAttribute("account", new Taikhoan());

        return "TrangChu";
    }
}


