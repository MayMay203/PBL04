package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.dao.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionServiceImple implements SuggestionService {
    private final SuggestionRepository suggestionRepo;

    @Autowired
    public SuggestionServiceImple(SuggestionRepository suggestionRepo) {
        this.suggestionRepo = suggestionRepo;
    }

    @Override
    public List<Dexuat> getSuggestionNConf() {
        return suggestionRepo.getSuggestionNConf();
    }

    @Override
    public List<Dexuat> getAllSuggest() {
        return suggestionRepo.getAllSuggestion();
    }


    @Override
    public List<Dexuat> getSuggestionByTitle(String IdTitle){
      return suggestionRepo.getSuggestionByTitle(IdTitle);
    }

    @Override
    public List<Dexuat> getSuggestionByIdTitle(Integer IdTitle) {
        return suggestionRepo.getSuggestionByIdTitle(IdTitle);
    }

    @Override
    public void Save(Dexuat dexuat) {
        suggestionRepo.save(dexuat);
    }

    @Override
    @Transactional
    public void confirmSugg(Integer idSugg) {
        suggestionRepo.confirmSugg(idSugg);
    }
    @Override
    @Transactional
    public void cancelSugg(Integer idSugg) {
        Dexuat sugg = suggestionRepo.getSuggByID(idSugg);
        suggestionRepo.delete(sugg);
    }
}

