package com.example.pbl04.service;

import com.example.pbl04.dao.SummaryRepository;
import com.example.pbl04.entity.Anhtongket;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.entity.Tongket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryServiceImple implements SummaryService{
    public SummaryRepository summaryRepository;
    @Autowired
    private SummaryServiceImple(SummaryRepository summaryRepository){ this.summaryRepository=summaryRepository;}
    @Override
    public List<Tongket> getSummaryList() {return summaryRepository.findAll();}
    public Tongket getSummaryByID(Integer id) {return summaryRepository.getSummaryByID(id);}

    @Override
    public Taikhoan getOrganizator(Integer id) {
        return summaryRepository.getOrganizator(id);
    }
    public Thanhvien getMemberByID(Integer id){return summaryRepository.getMemberByID(id);}
    public List<Thanhvien> getMemberList(Integer id){return summaryRepository.getMemberList(id);}
    public List<Anhtongket> getimgSummaryList(Integer id) {return  summaryRepository.getimgSummaryList(id);}
    public List<Tongket> getSummaryListByName(String name) {return summaryRepository.getSummaryListByName(name);}
}
