package com.example.pbl04.controller;

import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.pbl04.entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    private final AccountService accountService;
    private final MemberService memberService;
    @Autowired
    public LoginController(AccountService accountService, MemberService memberService) {
        this.accountService = accountService;
        this.memberService = memberService;
    }

    @GetMapping("/dang-ky")
    public String getLogin(){
        return "DangKy";
    }

    @GetMapping("/nhan-du-lieu-tai-khoan")
    @ResponseBody
    public ResponseEntity<Thanhvien> getAccountFromNotify(@RequestParam("id") Integer id){
        Thanhvien thanhvien = memberService.findMemberByID(id);
        return ResponseEntity.ok(thanhvien);
    }
}
