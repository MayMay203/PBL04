package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SuggestionController {
    private final SuggestionService suggestService;

    @Autowired
    public SuggestionController(SuggestionService suggestService) {
        this.suggestService = suggestService;
    }

    @GetMapping("/de-xuat")
    public String showSuggest(Model model,@RequestParam(name="position",required=false)String position,@RequestParam(name="title",required=false)String title) {
        List<Dexuat> suggestionList = suggestService.getAllSuggest();
        List<Integer> countSugg = suggestService.CountSuggestion();
        model.addAttribute("suggestionList",suggestionList);
        System.out.println(suggestionList.size());
        System.out.println(countSugg.size());
        model.addAttribute("countSugg",countSugg);
        return "DeXuat";
    }

}
