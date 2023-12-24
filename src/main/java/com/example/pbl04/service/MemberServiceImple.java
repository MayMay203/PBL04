package com.example.pbl04.service;

import com.example.pbl04.dao.MemberRepository;
import com.example.pbl04.entity.Dangky;
import com.example.pbl04.entity.Thanhvien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Thanhvien> getMembersByRegis(List<Dangky> registerList) {
        List<Thanhvien> membersList = new ArrayList<>();
        for(Dangky reg : registerList){
            membersList.add(memberRepository.findMemberByID(reg.getMaTK().getId()));
        }
        return membersList;
    }

    @Override
    public void updateInfor(Thanhvien thanhvien) {
        memberRepository.save(thanhvien);
    }
    @Override
    public List<Thanhvien> getMemberNeedConfirmByIDHD(Integer maHD)
    {
        return memberRepository.getMemberNeedConfirmByIDHD(maHD);
    }
    public void ConfirmMember(Integer maHD, Integer maTK)
    {
        memberRepository.ConfirmMember(maHD,maTK);
    }
    public void CancelMember(Integer maHD, Integer maTK)
    {
        memberRepository.CancelMember(maHD,maTK);
    }

    @Override
    public void addMember(Thanhvien thanhvien) {
        memberRepository.save(thanhvien);
    }
}
