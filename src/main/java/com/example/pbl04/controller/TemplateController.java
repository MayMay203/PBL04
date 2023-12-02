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

    @GetMapping("/trang-chu")
    public String getHome()
    {
        return "TrangChu";
    }

    @GetMapping("/trang-chu-hoat-dong")
    public String getActivityHome()
    {
        return "TrangChuHoatDong";
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome()
    {
        return "TrangChuDanhGia";
    }

    @GetMapping("/trang-chu-tong-ket")
    public String getSummaryHome()
    {
        return "TrangChuTongKet";
    }

    @GetMapping("/trang-ca-nhan")
    public String getPersonality()
    {
        return "TrangCaNhan";
    }
}
