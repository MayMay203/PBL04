package com.example.pbl04.service;

import com.example.pbl04.dao.ImageSummaryRepository;
import com.example.pbl04.dao.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.pbl04.entity.*;
@Service
public class SummaryServiceImple implements SummaryService{
    private final SummaryRepository summaryRepository;
    private final ImageSummaryRepository imageSummaryRepository;
    @Autowired
    private SummaryServiceImple(SummaryRepository summaryRepository,ImageSummaryRepository imageSummaryRepository){
        this.summaryRepository=summaryRepository;
        this.imageSummaryRepository=imageSummaryRepository;
    }
    @Override
    public List<Tongket> getSummaryList() {return summaryRepository.findAll();}
    public Tongket getSummaryByID(Integer id) {return summaryRepository.getSummaryByID(id);}
    public Tongket getSummaryByIDTK(Integer id) {return summaryRepository.getSummaryByIDTK(id);}
    @Override
    public Taikhoan getOrganizator(Integer id) {
        return summaryRepository.getOrganizator(id);
    }
    public Thanhvien getMemberByID(Integer id){return summaryRepository.getMemberByID(id);}
    public List<Thanhvien> getMemberList(Integer id){return summaryRepository.getMemberList(id);}
    public List<Anhtongket> getimgSummaryList(Integer id) {return  summaryRepository.getimgSummaryList(id);}
    public List<Tongket> getSummaryListByName(String name) {return summaryRepository.getSummaryListByName(name);}
    public Tongket addSummary(Tongket tongket) {
       return summaryRepository.save(tongket);
    }
    public void addImages(Anhtongket anhtongket){
        imageSummaryRepository.save(anhtongket);
    }

}
