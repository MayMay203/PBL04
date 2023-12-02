package com.example.pbl04.controller;

import com.example.pbl04.service.EvaluateService;
import com.example.pbl04.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
//    @GetMapping("/danh-gia")
//    public List<Danhgia> getEvaluate(){
//       return evaluateService.getAllEvaluate();
//    }
}
