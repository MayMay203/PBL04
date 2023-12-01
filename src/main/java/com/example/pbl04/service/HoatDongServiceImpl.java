package com.example.pbl04.service;

import com.example.pbl04.dao.HoatDongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoatDongServiceImpl implements HoatDongService{
    private final HoatDongRepository hoatDongRepository;
    @Autowired
    public HoatDongServiceImpl(HoatDongRepository hoatDongRepository)
    {
        this.hoatDongRepository=hoatDongRepository;
    }
}
