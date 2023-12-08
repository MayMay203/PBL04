package com.example.pbl04.controller;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonalityController {
    private final SessionService sessionService;
    private final ActivityService activityService;
    @Autowired
    public PersonalityController(SessionService sessionService, ActivityService activityService) {
        this.sessionService = sessionService;
        this.activityService = activityService;
    }

    @GetMapping("/trang-ca-nhan")
    public String show(Model model, @RequestParam("id") Integer id, HttpSession session){
        List<Hoatdong> actListByHost = activityService.getActivityByHost(id);
        model.addAttribute("actListByHost",actListByHost);
        model.addAttribute("activity", new Hoatdong());
//        Dangky getRegisterInfo = activityService.getRegisterInfo(id)
        sessionService.createSessionModel(model, session);
        return "TrangCaNhan";
    }
}
