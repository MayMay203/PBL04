package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.RegisterService;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.TopicService;
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
    private final TopicService topicService;
    private final RegisterService registerService;
    @Autowired
    public ActivityController(ActivityService activityService, TopicService topicService, SessionService sessionService, RegisterService registerService){
        this.activityService=activityService;
        this.topicService = topicService;
        this.sessionService = sessionService;
        this.registerService = registerService;
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
//    @GetMapping("/trang-ca-nhan")
//    public String showModalThemHoatDong(Model model, HttpSession session){
//        model.addAttribute("activity", new Hoatdong());
//        sessionService.createSessionModel(model, session);
//        return  "TrangCaNhan";
//    }

    @PostMapping("/addActivity")
    @ResponseBody
    public Map<String, Object> addActivity(@RequestParam("tenChuDe") String tenChuDe,
                                           @RequestParam("tenHD") String tenHD,
                                           @RequestParam("diaDiem") String diaDiem,
                                           @RequestParam("thoiGianBD") String thoiGianBD,
                                           @RequestParam("thoiGianKT") String thoiGianKT,
                                           @RequestParam("sotnvtt") String sotnvtt,
                                           @RequestParam("sotnvtd") String sotnvtd,
                                           @RequestParam("moTa") String moTa,
                                           @RequestParam("anh") String anh,
                                           @RequestParam Integer userID, HttpSession session, Model model)
//            , HttpSession session)
    {

        Map<String, Object> response = new HashMap<>();
        Chude chude = topicService.getChuDeByTen(tenChuDe);
        if(chude.getTenChuDe() !=null  )
        {
            Integer machude = chude.getId();
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh, userID);
        }else {
            topicService.addChude(chude);
            Integer machude = topicService.getMaChuDeByTen(tenChuDe);
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh, userID);
        }
        sessionService.createSessionModel(model, session);
        response.put("message", "Hoạt động đã được thêm mới thành công");
        response.put("success", true);
        return response;
    }
    @RequestMapping(value="/Join-Acti")
    public String JoinActivity(@RequestParam Integer maHD,Model model, HttpSession session)
    {
        sessionService.createSessionModel(model, session);
        Taikhoan taikhoan = (Taikhoan) model.getAttribute("account");
        registerService.joinActivity(maHD,taikhoan.getId());
        return null;
    }
}
