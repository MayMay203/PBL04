package com.example.pbl04.service;

import com.example.pbl04.dao.MemberRepository;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImple implements MemberService{

    private MemberRepository memberRepository;
    @Autowired
    public MemberServiceImple(MemberRepository  memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Thanhvien> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Thanhvien findMemberByID(int maTK) {
        return memberRepository.findMemberByID(maTK);
    }

    @Override
    public void updateInfor(Thanhvien thanhvien) {
        memberRepository.save(thanhvien);
    }
}
