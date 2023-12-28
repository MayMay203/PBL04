package com.example.pbl04.service;
import com.example.pbl04.entity.*;
import java.util.List;

public interface SuggestionService {
    List<Dexuat> getAllSuggest();
    List<Dexuat> getSuggestionNConf();
    List<Dexuat> getSuggestionByTitle(String NameTitle);
    List<Dexuat> getSuggestionByIdTitle(Integer NameTitle);
//    List<Integer> CountSuggestion(List<Dexuat> suggestionList);

    void Save(Dexuat dexuat);

    void confirmSugg(Integer idSugg);

    void cancelSugg(Integer idSugg);
    Dexuat getSuggById(Integer idSugg);

}
