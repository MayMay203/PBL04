package com.example.pbl04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/dang-ky")
    public String getLogin(){
        return "DangKy";
    }
}
