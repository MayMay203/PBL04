package com.example.pbl04.service;

import com.example.pbl04.entity.Chude;

public interface TopicService {
    Chude getChuDeByTen(String tenchude) ;
    void addChude(Chude chude);
    Integer getMaChuDeByTen(String tenchude);
}
