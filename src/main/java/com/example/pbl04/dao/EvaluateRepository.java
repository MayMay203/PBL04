package com.example.pbl04.dao;

import com.example.pbl04.entity.Danhgia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluateRepository extends JpaRepository<Danhgia,Integer> {
}
