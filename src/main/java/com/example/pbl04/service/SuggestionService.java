package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;

import java.util.List;
import java.util.Optional;

public interface SuggestionService {
    List<Dexuat> getAllSuggest();
    List<Dexuat> getSuggestionByTitle(Optional<String> title);
    List<Integer> CountSuggestion();
}
