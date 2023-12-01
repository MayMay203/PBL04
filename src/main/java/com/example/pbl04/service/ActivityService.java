package com.example.pbl04.service;


import com.example.pbl04.entity.Hoatdong;

import java.util.List;

public interface ActivityService {
    List<Hoatdong> getAllActivity();
    Hoatdong getActivityByID(Integer id);
    List<Hoatdong> getActivityOccured();
}
