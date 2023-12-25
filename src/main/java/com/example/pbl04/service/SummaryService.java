package com.example.pbl04.service;

import com.example.pbl04.entity.Anhtongket;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.entity.Tongket;

import java.util.List;

public interface SummaryService  {
    List<Tongket> getSummaryList();
    Tongket getSummaryByID(Integer id);
    Taikhoan getOrganizator(Integer id);
    Thanhvien getMemberByID(Integer id);
    List<Thanhvien> getMemberList(Integer id);
    List<Anhtongket> getimgSummaryList(Integer id);
    List<Tongket> getSummaryListByName(String nameSummary);
    Tongket addSummary(Tongket tongket);
    void addImages(Anhtongket anhtongket);

}
