package com.example.pbl04.controller;

import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    private final ActivityService activityService;
    private final SessionService sessionService;
    @Autowired
    public ActivityController(ActivityService activityService, SessionService sessionService){
        this.activityService=activityService;
        this.sessionService = sessionService;
    }

   @GetMapping("/trang-chu-hoat-dong")
    public String showActivityOccured(Model model, HttpSession session)
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
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
        return "TrangChuHoatDong";
    }
    @RequestMapping(value ="/Join")
    public String showActivityByID(Model model,@RequestParam("id") Integer id, HttpSession session)
    {
        Taikhoan taikhoan =activityService.getOrganizator(id);
        List<Thanhvien> thanhvienList =activityService.getMemberList(id);
        Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
        Hoatdong hoatdong = activityService.getActivityByID(id);
        model.addAttribute("Anh",new Anh());
        model.addAttribute("hoatdong",hoatdong);
        model.addAttribute("taikhoan",taikhoan);
        model.addAttribute("thanhvien",thanhvien);
        model.addAttribute("thanhvienList",thanhvienList);
        sessionService.createSessionModel(model, session);
        return "ChiTietHoatDong";
    }
    @GetMapping("/them-hoat-dong")
    public String showModalThemHoatDong(Model model, HttpSession session){
        model.addAttribute("activity", new Hoatdong());
        sessionService.createSessionModel(model, session);
        return  "TrangCaNhan";
    }
    @PostMapping(value="/addActivity")
    @ResponseBody
    public Map<String, Object> addActivity( @RequestParam String tenChuDe, @RequestParam String tenhd)
    {
        Map<String, Object> response = new HashMap<>();
//       model.addAttribute("activity", new Hoatdong());
        return response;
    }
}
