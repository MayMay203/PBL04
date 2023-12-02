package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;

import java.util.List;

public interface SuggestService{
    List<Dexuat> getAllSuggest();
    int CountSuggest(String position, String title);
}
