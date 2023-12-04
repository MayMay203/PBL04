package com.example.pbl04.controller;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ActivityController {
    private final ActivityService activityService;
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService=activityService;
    }

   @GetMapping("/trang-chu-hoat-dong")
    public String showActivityOccured(Model model)
    {

        Integer numberParticipants= activityService.getParticipants();
        Integer numberActivitys= activityService.getNumActivity();
//        List<Hoatdong> actiListUpcomming1 =activityService.getActivityUpcomming();
        List<Dangky> actiListUpcomming =activityService.getActivityUpcomming();
        List<Hoatdong> actiList = activityService.getActivityOccured();
        List<Hoatdong> actiListHappening= activityService.getActivityHappening();
        model.addAttribute("numberActivitys",numberActivitys);
        model.addAttribute("Anh",new Anh());
        model.addAttribute("actiList",actiList);
        model.addAttribute("actiListHappening",actiListHappening);
        model.addAttribute(("actiListUpcomming"),actiListUpcomming);
        model.addAttribute("numberParticipants",numberParticipants);
        return "TrangChuHoatDong";
    }
    @RequestMapping(value ="/Join", method = RequestMethod.GET)
    public String showActivityByID(Model model, @ModelAttribute Hoatdong hd)
    {
        Taikhoan taikhoan =activityService.getOrganizator(hd.getId());
        List<Thanhvien> thanhvienList =activityService.getMemberList(hd.getId());
        Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
        Hoatdong hoatdong = activityService.getActivityByID(hd.getId());
        model.addAttribute("Anh",new Anh());
        model.addAttribute("hoatdong",hoatdong);
        model.addAttribute("taikhoan",taikhoan);
        model.addAttribute("thanhvien",thanhvien);
        model.addAttribute("thanhvienList",thanhvienList);
        return "ChiTietHoatDong";
    }
}
