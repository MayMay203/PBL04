package com.example.pbl04.controller;

import com.example.pbl04.entity.Chude;
import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.SuggestionService;
import com.example.pbl04.service.TopicService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final SessionService sessionService;
    private final TopicService topicService;
    private final AccountService accountService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService, SessionService sessionService,
                                TopicService topicService,AccountService accountService) {
        this.suggestionService = suggestionService;
        this.sessionService = sessionService;
        this.topicService = topicService;
        this.accountService = accountService;
    }

    @GetMapping("/de-xuat")
    public String showSuggest(Model model, HttpSession session) {
        List<Dexuat> suggestionList = suggestionService.getAllSuggest();
        List<Integer> countSugg = suggestionService.CountSuggestion(suggestionList);
        List<Chude> topicsList = topicService.getAllTopics();
        model.addAttribute("suggestionList",suggestionList);
        model.addAttribute("countSugg",countSugg);
        model.addAttribute("topicsList",topicsList);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
        return "DeXuat";
    }

    @GetMapping("/de-xuat/{IdTitle}")
    @ResponseBody
    public ResponseEntity<List<Dexuat>> showSuggestionByTitle(@PathVariable(name = "IdTitle", required = false) Integer IdTitle) {
       // String str = title.replaceAll("-"," ");
       // System.out.println(str);
        List<Dexuat> suggestionList = suggestionService.getSuggestionByIdTitle(IdTitle);
        return ResponseEntity.ok(suggestionList);
    }

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
        dexuat.setViTri(thonPhuong + quanHuyen + tinhThanhPho);
        dexuat.setMaChuDe(topicService.getTopicByID(maChuDe));
        dexuat.setMoTa(moTa);
        suggestionService.Save(dexuat);
//        redirectAttributes.addFlashAttribute("successeMessage","Thêm đề xuất thành công!");
        return "redirect:de-xuat";
    }



}

