package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuggestController {
    private final SuggestService suggestService;
    @Autowired
    public SuggestController(SuggestService suggestService){
        this.suggestService = suggestService;
    }
    @GetMapping("/de-xuat")
    public String showSuggest(Model model){
        List<Dexuat> suggestList = suggestService.getAllSuggest();
        model.addAttribute("suggestList", suggestList);
        // In thông tin suggestList ra console
        return "DeXuat";
    }
}
