package com.example.pbl04.service;

import com.example.pbl04.dao.SuggestionRepository;
import com.example.pbl04.entity.Dexuat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    @Override
//    public List<Dexuat> getSuggestionByTitle(Optional<String> title) {
//        return suggestionDAO;
//    }
    @Override
    public List<Dexuat> getSuggestionByTitle(Optional<String> title){
      return suggestionRepo.getSuggestionByTitle(title);
    }
    @Override
    public List<Integer> CountSuggestion() {
        return suggestionRepo.countSuggestion();
    }
}

