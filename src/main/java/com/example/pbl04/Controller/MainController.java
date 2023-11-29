package com.example.pbl04.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/Danh-gia")
    public String DanhGia(){
        return "DanhGia.html";
    }

    @GetMapping("/De-xuat")
    public String DeXuat(){
        return "DeXuat.html";
    }

    @GetMapping("/Trang-Chu-Danh-Gia")
    public String Trangchudanhgia(){
        return "TrangChuDanhGia.html";
    }



}
