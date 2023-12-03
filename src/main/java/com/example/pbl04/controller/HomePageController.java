package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final MemberService memberService;
    @Autowired
    public HomePageController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/trang-chu")
    public String showMember(Model model){
        List<Thanhvien> memberList = memberService.getAllMember();
        model.addAttribute("memberList", memberList);
        return "TrangChu";
    }


}
