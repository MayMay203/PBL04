package com.example.pbl04.service;

import com.example.pbl04.dao.AboutUsRepository;
import com.example.pbl04.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AboutUsServiceImple implements AboutUsService {
    @Autowired
    private final AboutUsRepository aboutUsRepository;

    public AboutUsServiceImple(AboutUsRepository aboutUsRepository) {
        this.aboutUsRepository = aboutUsRepository;
    }

    @Override
    public List<Vetrangweb> getAllAboutUs() {
        return aboutUsRepository.findAll();
    }


}
