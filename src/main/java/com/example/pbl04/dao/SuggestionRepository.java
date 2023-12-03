package com.example.pbl04.dao;

import com.example.pbl04.entity.Dexuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Dexuat,Integer> {

    @Query("select d from Dexuat  d group by d.viTri,d.chuDe")
    public List<Dexuat> getAllSuggestion();

    @Query("select count(*) from Dexuat d group by d.viTri,d.chuDe")
     public List<Integer> countSuggestion();

}
