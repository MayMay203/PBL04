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
        sessionService.createSessionModel(model, session);
        Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
        Dangky checkDangky = registerService.getDangKyByHDTK(myaccount.getId(), id);
        Taikhoan taikhoan =activityService.getOrganizator(id);
        List<Thanhvien> thanhvienList =activityService.getMemberList(id);
        Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
        Hoatdong hoatdong = activityService.getActivityByID(id);
        model.addAttribute("hoatdong",hoatdong);
        model.addAttribute("taikhoan",taikhoan);
        model.addAttribute("thanhvien",thanhvien);
        model.addAttribute("thanhvienList",thanhvienList);
        model.addAttribute("checkDangky",checkDangky);
        return "ChiTietHoatDong";
    }
//    @GetMapping("/them-hoat-dong")
//    public String showModalThemHoatDong(Model model, HttpSession session){
//        model.addAttribute("activity", new Hoatdong());
//        sessionService.createSessionModel(model, session);
//        return  "ThemHoatDong";
//    }

    @PostMapping(value="/addActivity")
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
                                           @RequestParam("maTK") String maTK,
                                           Model model)
    {

        Map<String, Object> response = new HashMap<>();
        Chude chude = topicService.getChuDeByTen(tenChuDe);
        if(chude.getTenChuDe() !=null  )
        {
            Integer machude = chude.getId();
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh,maTK);
        }else {
            topicService.addChude(chude);
            Integer machude = topicService.getMaChuDeByTen(tenChuDe);
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh,maTK);
        }

        response.put("message", "Hoạt động đã được thêm mới thành công");
        response.put("success", true);
        return response;
    }
    @PostMapping(value="/Regis-activity")
    @ResponseBody
    public String JoinActivity(@RequestParam("maHD") Integer maHD,
                               @RequestParam("maTK") Integer maTK
                               )
    {
        System.out.println(maHD);
        System.out.println(maTK);
        registerService.joinActivity(maHD,maTK);
        return "redirect:/ChiTietHoatDong";
    }
    @GetMapping("/get-activity-by-id")
    @ResponseBody
    public Hoatdong getActivityByID(@RequestParam("maHD") Integer maHD)
    {
        Hoatdong hd = activityService.getActivityByID(maHD);
        return hd;
    }
}
