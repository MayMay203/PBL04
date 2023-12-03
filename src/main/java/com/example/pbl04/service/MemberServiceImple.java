package com.example.pbl04.service;

import com.example.pbl04.dao.MemberRepository;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImple implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Thanhvien> getAllMember() {
        return memberRepository.findAll();
    }
}
