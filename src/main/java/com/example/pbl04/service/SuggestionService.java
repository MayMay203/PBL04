package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;

import java.util.List;

public interface SuggestionService {
    List<Dexuat> getAllSuggest();
    List<Dexuat> getSuggestionByTitle(String NameTitle);
    List<Dexuat> getSuggestionByIdTitle(Integer NameTitle);
    List<Integer> CountSuggestion(List<Dexuat> suggestionList);


}
