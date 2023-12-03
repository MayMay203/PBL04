package com.example.pbl04.dao;

import com.example.pbl04.entity.Danhgia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Danhgia,Integer> {
}
