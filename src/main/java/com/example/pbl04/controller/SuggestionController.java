package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.service.SuggestionService;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SuggestionController {
    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/de-xuat")
    public String showSuggest(Model model) {
        List<Dexuat> suggestionList = suggestionService.getAllSuggest();
        List<Integer> countSugg = suggestionService.CountSuggestion();
        model.addAttribute("suggestionList",suggestionList);
        model.addAttribute("countSugg",countSugg);
        return "DeXuat";
    }

    @GetMapping("/de-xuat/{title}")
    @ResponseBody
    public ResponseEntity<List<Dexuat>> showSuggestionByTitle(@PathVariable(name = "title", required = false) Optional<String> title) {
        List<Dexuat> suggestionList = suggestionService.getSuggestionByTitle(title);
        return ResponseEntity.ok(suggestionList);
    }
}

