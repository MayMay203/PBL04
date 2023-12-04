package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.SummaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SummaryController {
    private final SummaryService summaryService;
    public SummaryController(SummaryService summaryService) {this.summaryService=summaryService;}
    @GetMapping("/trang-chu-tong-ket")
    public String showSummaryList(Model model)
    {
        List<Tongket> summaryList = summaryService.getSummaryList();
        model.addAttribute("summaryList",summaryList);
        return "TrangChuTongKet";
    }
    @RequestMapping(value ="/View-Summary", method = RequestMethod.GET)
    public String showDetailSummary(Model model, @ModelAttribute Tongket tongket)
    {
        Tongket summary = summaryService.getSummaryByID(tongket.getId());

        if (summary != null && summary.getMaHD() != null) {
            Taikhoan taikhoan = summaryService.getOrganizator(summary.getMaHD().getId());
            List<Thanhvien> thanhvienList = summaryService.getMemberList(summary.getMaHD().getId());
//            List<Anhtongket> imgSummaryList = summaryService.getimgSummaryList(tongket.getId());
            Thanhvien thanhvien = summaryService.getMemberByID(taikhoan.getId());
            model.addAttribute("Anh",new Anh());
            model.addAttribute("summary", summary);
            model.addAttribute("taikhoan", taikhoan);
            model.addAttribute("thanhvienList", thanhvienList);
            model.addAttribute("thanhvien", thanhvien);
//            model.addAttribute("imgSummaryList",imgSummaryList);
            return "TongKetHoatDong";
        } else {
            return "errorPage";
        }
    }

}
