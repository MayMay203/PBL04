package com.example.pbl04.dao;

import com.example.pbl04.entity.Danhgia;
import com.example.pbl04.entity.Hoatdong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Danhgia,Integer> {
    @Query("SELECT h.diemTNV FROM Danhgia h WHERE h.id = :id")
    List<Integer> rateOfMember(int id);

}
