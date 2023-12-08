package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.SuggestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final SessionService sessionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService, SessionService sessionService) {
        this.suggestionService = suggestionService;
        this.sessionService = sessionService;
    }

    @GetMapping("/de-xuat")
    public String showSuggest(Model model, HttpSession session) {
        List<Dexuat> suggestionList = suggestionService.getAllSuggest();
        List<Integer> countSugg = suggestionService.CountSuggestion(suggestionList);
        model.addAttribute("suggestionList",suggestionList);
        model.addAttribute("countSugg",countSugg);
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

}

