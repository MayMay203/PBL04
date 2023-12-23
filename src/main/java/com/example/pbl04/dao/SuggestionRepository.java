package com.example.pbl04.dao;

import com.example.pbl04.entity.Dexuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Dexuat,Integer> {

    @Query("SELECT d FROM Dexuat d WHERE d.maChuDe.tenChuDe like %:NameTitle% and d.tinhTrangDuyet=true")
    List<Dexuat> getSuggestionByTitle(String NameTitle);

    @Query("SELECT d FROM Dexuat d WHERE d.maChuDe.id = :IdTitle and d.tinhTrangDuyet=true")
    List<Dexuat> getSuggestionByIdTitle(Integer IdTitle);
    
    @Query("select d from Dexuat  d where d.tinhTrangDuyet = true")
    public List<Dexuat> getAllSuggestion();

//    @Query("select count(*) from Dexuat where maChuDe.id = :IdTitle and tinhTrangDuyet = true")
//     public List<Integer> countSuggestion(Integer IdTitle);

}
