package com.example.pbl04.controller;

import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    public SuggestionController(SuggestionService suggestionService, SessionService sessionService,
                                TopicService topicService,AccountService accountService,
                                ActivityService activityService) {
        this.suggestionService = suggestionService;
        this.sessionService = sessionService;
        this.topicService = topicService;
        this.accountService = accountService;
        this.activityService = activityService;
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
                                Model model, HttpSession session,
                                RedirectAttributes redirectAttributes){
        Dexuat dexuat = new Dexuat();
        sessionService.createSessionModel(model,session);
        //Lay ra tk da dang nhap
        Taikhoan account =(Taikhoan)model.getAttribute("account");
        Taikhoan accUser = accountService.getTaiKhoanByID(account.getId());
        dexuat.setMaTK(accUser);
        dexuat.setViTri(thonPhuong + " " +  quanHuyen + " " + tinhThanhPho);
        dexuat.setMaChuDe(topicService.getTopicByID(maChuDe));
        dexuat.setMoTa(moTa);
        suggestionService.Save(dexuat);
        System.out.println("Đề xuất mới nè " + dexuat.getId());
//        redirectAttributes.addFlashAttribute("successeMessage","Thêm đề xuất thành công!");
        return "redirect:de-xuat";
    }



}

