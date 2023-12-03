package com.example.pbl04.service;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    List<Thanhvien> getAllMember();
}
