package com.example.pbl04.service;

import com.example.pbl04.dao.SuggestionRepository;
import com.example.pbl04.entity.Dexuat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImple implements SuggestionService {
    public SuggestionRepository suggestionRepo;

    @Autowired
    private SuggestionServiceImple(SuggestionRepository suggestionRepo) {
        this.suggestionRepo = suggestionRepo;
    }

    @Override
    public List<Dexuat> getAllSuggest() {
        return suggestionRepo.getAllSuggestion();
    }

    @Override
    public List<Integer> CountSuggestion() {
        return suggestionRepo.countSuggestion();
    }
}

