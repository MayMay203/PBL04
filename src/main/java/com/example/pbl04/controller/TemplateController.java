package com.example.pbl04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("/header")
    public String getHeader()
    {
        return "Header";
    }

//    @GetMapping("/trang-ca-nhan")
//    public String getPersonality()
//    {
//        return "TrangCaNhan";
//    }


}
