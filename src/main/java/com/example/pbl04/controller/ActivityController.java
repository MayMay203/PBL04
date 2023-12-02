package com.example.pbl04.controller;

import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        List<Hoatdong> actiListUpcomming =activityService.getActivityUpcomming();
        List<Hoatdong> actiList = activityService.getActivityOccured();
        model.addAttribute("numberActivitys",numberActivitys);
        model.addAttribute("actiList",actiList);
        model.addAttribute(("actiListUpcomming"),actiListUpcomming);
        model.addAttribute("numberParticipants",numberParticipants);
        return "TrangChuHoatDong";
    }
    public String showActivityByID(Model model,   @PathVariable Integer id)
    {
        Hoatdong hoatdong = activityService.getActivityByID(id);
        model.addAttribute("hoatdong",hoatdong);
        return "ChiTietHoatDong";
    }
}
