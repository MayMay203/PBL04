package com.example.pbl04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.pbl04.entity.*;
@Controller
public class LoginController {
    @GetMapping("/dang-ky")
    public String getLogin(){
        return "DangKy";
    }
}
