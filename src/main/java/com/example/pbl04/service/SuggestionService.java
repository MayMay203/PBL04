package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;

import java.util.List;

public interface SuggestionService {
    List<Dexuat> getAllSuggest();
    List<Integer> CountSuggestion();
}