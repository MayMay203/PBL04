package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.dao.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    @Override
//    public List<Dexuat> getSuggestionByTitle(Optional<String> title) {
//        return suggestionDAO;
//    }
    @Override
    public List<Dexuat> getSuggestionByTitle(String IdTitle){
      return suggestionRepo.getSuggestionByTitle(IdTitle);
    }
    @Override
    public List<Integer> CountSuggestion(List<Dexuat> suggestionList) {
        List<Integer> count = new ArrayList<>();
        for(Dexuat sugg : suggestionList){
            count.add(suggestionRepo.countSuggestion(sugg.getMaChuDe().getId()).get(0));
        }
        return count;
    }
}

