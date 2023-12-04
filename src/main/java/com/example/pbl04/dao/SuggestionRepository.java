package com.example.pbl04.dao;

import com.example.pbl04.entity.Dexuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SuggestionRepository extends JpaRepository<Dexuat,Integer> {
//    Truy vấn còn sai
    @Query("SELECT d FROM Dexuat d WHERE d.chuDe LIKE :title")
    List<Dexuat> getSuggestionByTitle(@Param("title") Optional<String> title);
    
    @Query("select d from Dexuat  d group by d.viTri,d.chuDe")
    public List<Dexuat> getAllSuggestion();

    @Query("select count(*) from Dexuat d group by d.viTri,d.chuDe")
     public List<Integer> countSuggestion();

}
