package com.example.pbl04.dao;

import com.example.pbl04.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Dexuat,Integer> {

    @Query("SELECT d FROM Dexuat d WHERE d.maChuDe.tenChuDe like %:NameTitle% and d.tinhTrangDuyet=true")
    List<Dexuat> getSuggestionByTitle(String NameTitle);

    @Query("select d from Dexuat d where d.tinhTrangDuyet = false")
    List<Dexuat> getSuggestionNConf();

    @Query("SELECT d FROM Dexuat d WHERE d.maChuDe.id = :IdTitle and d.tinhTrangDuyet=true")
    List<Dexuat> getSuggestionByIdTitle(Integer IdTitle);
    
    @Query("select d from Dexuat  d where d.tinhTrangDuyet = true")
    List<Dexuat> getAllSuggestion();

    @Modifying
    @Query("update Dexuat d set d.tinhTrangDuyet = true where d.id = :idSugg")
    void confirmSugg(Integer idSugg);

    @Query("select d from Dexuat d where d.id = :idSugg")
    Dexuat getSuggByID(Integer idSugg);

}
