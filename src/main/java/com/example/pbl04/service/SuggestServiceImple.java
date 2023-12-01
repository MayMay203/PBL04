package com.example.pbl04.service;

import com.example.pbl04.dao.SuggestionRepository;
import com.example.pbl04.entity.Dexuat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImple implements SuggestService{
    public SuggestionRepository suggestionRepo;
    @Autowired
    private SuggestServiceImple(SuggestionRepository suggestionRepo){
        this.suggestionRepo = suggestionRepo;
    }
    @Override
    public List<Dexuat> getAllSuggest() {
        return suggestionRepo.findAll();
    }
}
