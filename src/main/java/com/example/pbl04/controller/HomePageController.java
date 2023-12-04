package com.example.pbl04.controller;

import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.EvaluationService;
import com.example.pbl04.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomePageController {
    private final MemberService memberService;
    private final EvaluationService evaluationService;
    @Autowired
    public HomePageController(MemberService memberService, EvaluationService evaluationService){
        this.memberService = memberService;
        this.evaluationService = evaluationService;
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
        return "TrangChu";
    }
}


