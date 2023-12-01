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
    private ActivityService activityService;
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService=activityService;
    }

    public String showActivity(Model model)
    {
        List<Hoatdong> actiList= activityService.getAllActivity();
        model.addAttribute("actiList",actiList);
        return "TrangChuHoatDong";
    }
    @GetMapping("/hoat-dong-3")
    public String showActivityOccured(Model model)
    {
        List<Hoatdong> actiList = activityService.getActivityOccured();
        model.addAttribute("actiList",actiList);
        return "TrangChuHoatDong";
    }
    public String showActivityByID(Model model,   @PathVariable Integer id)
    {
        Hoatdong hoatdong = activityService.getActivityByID(id);
        model.addAttribute("hoatdong",hoatdong);
        return "ChiTietHoatDong";
    }
}
