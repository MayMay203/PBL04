package com.example.pbl04.controller;

import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import com.example.pbl04.entity.*;
@Controller
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final SessionService sessionService;
    private final TopicService topicService;
    private final AccountService accountService;
    private final ActivityService    activityService;
    private final NotificationService notificationService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService, SessionService sessionService,
                                TopicService topicService,AccountService accountService,
                                ActivityService activityService,
                                NotificationService notificationService) {
        this.suggestionService = suggestionService;
        this.sessionService = sessionService;
        this.topicService = topicService;
        this.accountService = accountService;
        this.activityService = activityService;
        this.notificationService = notificationService;
    }

    @GetMapping("/de-xuat")
    public String showSuggest(Model model, HttpSession session) {
        List<Dexuat> suggestionList = suggestionService.getAllSuggest();
        List<String> locationList  = new ArrayList<>();
        for(Dexuat sugg:suggestionList){
            locationList.add(sugg.getViTri());
        }
        //Dem hoat dong theo vi tri
        List<Integer> countAct = activityService.countActByLocation(locationList);
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("suggestionList",suggestionList);
        model.addAttribute("countAct",countAct);
        model.addAttribute("listTopics",listTopics);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
        //Nội dung thông báo
        Taikhoan myAcc = (Taikhoan) session.getAttribute("account");
        if(myAcc!=null){
            List<Thongbao> listNotice = new ArrayList<>();
            listNotice = notificationService.getNotifiByIdAcc(myAcc.getId());
            model.addAttribute("listNotice",listNotice);
            //Đếm thông báo chưa đọc
            Integer numOfNotice = notificationService.countNotice(myAcc.getId());
            model.addAttribute("numOfNotice",numOfNotice);
        }
        return "DeXuat";
    }

//    @GetMapping("/de-xuat/{IdTitle}")
//    @ResponseBody
//    public ResponseEntity<List<Dexuat>> showSuggestionByTitle(@PathVariable(name = "IdTitle", required = false) Integer IdTitle) {
//       // String str = title.replaceAll("-"," ");
//       // System.out.println(str);
//        List<Dexuat> suggestionList = suggestionService.getSuggestionByIdTitle(IdTitle);
//        return ResponseEntity.ok(suggestionList);
//    }

    @PostMapping("/them-de-xuat")
    public String addSuggestion(@RequestParam ("thonPhuong")String thonPhuong,
                                @RequestParam ("quanHuyen")String quanHuyen,
                                @RequestParam ("tinhThanhPho")String tinhThanhPho,
                                @RequestParam("chuDe")Integer maChuDe,
                                @RequestParam("moTa")String moTa,
                                Model model, HttpSession session){
        Dexuat dexuat = new Dexuat();
        sessionService.createSessionModel(model,session);
        //Lay ra tk da dang nhap
        Taikhoan account =(Taikhoan)model.getAttribute("account");
        Taikhoan accUser = accountService.getTaiKhoanByID(account.getId());
        dexuat.setMaTK(accUser);
        dexuat.setViTri(thonPhuong + " " +  quanHuyen + " " + tinhThanhPho);
        dexuat.setMaChuDe(topicService.getTopicByID(maChuDe));
        dexuat.setMoTa(moTa);
        dexuat.setThoiGianDeXuat(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        suggestionService.Save(dexuat);
        List<Taikhoan> accList = accountService.getAccountAd();

        Instant thoiGianTB = Instant.now();
        //Them thong bao cho de xuat
        for(Taikhoan acc : accList){
            Thongbao tb = new Thongbao();
            tb.setNoiDung("Một đề xuất mới đang chờ duyệt");
            tb.setLoaiTB(0);
            tb.setMa(dexuat.getId());
            tb.setMaTK(acc);
            tb.setThoiGianTB(thoiGianTB);
            notificationService.addNotification(tb);
        }
        return "redirect:de-xuat";
    }

    @GetMapping("/nhan-du-lieu-de-xuat")
    @ResponseBody
    public ResponseEntity<Dexuat> getNotificationList(@RequestParam("id") Integer id){
        Dexuat suggestion  = suggestionService.getSuggById(id);
        return ResponseEntity.ok(suggestion);
    }





}

