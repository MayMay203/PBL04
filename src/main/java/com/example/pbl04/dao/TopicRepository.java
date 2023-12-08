package com.example.pbl04.dao;

import com.example.pbl04.entity.Chude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Chude,Integer> {
    @Query("Select cd from Chude cd where cd.tenChuDe like :tenchude")
    Chude getChuDeByTen(String tenchude);

    @Query("SELECT cd from Chude cd where cd.id = :id")
    Chude getChudeByID(Integer id);
}