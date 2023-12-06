package com.example.pbl04.entity;

import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "danhgia")
public class Danhgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDG", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private Hoatdong maHD;

    @Column(name = "diemTC")
    private Integer diemTC;

    @Column(name = "tieuChi1")
    private Boolean tieuChi1;

    @Column(name = "tieuChi2")
    private Boolean tieuChi2;

    @Column(name = "tieuChi3")
    private Boolean tieuChi3;

    @Lob
    @Column(name = "binhLuan", nullable = false)
    private String binhLuan;

    @Column(name = "thoiGianBL", nullable = false)
    private Instant thoiGianBL;

    @Column(name = "diemTNV")
    private Integer diemTNV;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taikhoan getMaTK() {
        return maTK;
    }

    public void setMaTK(Taikhoan maTK) {
        this.maTK = maTK;
    }

    public Hoatdong getMaHD() {
        return maHD;
    }

    public void setMaHD(Hoatdong maHD) {
        this.maHD = maHD;
    }

    public Integer getDiemTC() {
        return diemTC;
    }

    public void setDiemTC(Integer diemTC) {
        this.diemTC = diemTC;
    }

    public Boolean getTieuChi1() {
        return tieuChi1;
    }

    public void setTieuChi1(Boolean tieuChi1) {
        this.tieuChi1 = tieuChi1;
    }

    public Boolean getTieuChi2() {
        return tieuChi2;
    }

    public void setTieuChi2(Boolean tieuChi2) {
        this.tieuChi2 = tieuChi2;
    }

    public Boolean getTieuChi3() {
        return tieuChi3;
    }

    public void setTieuChi3(Boolean tieuChi3) {
        this.tieuChi3 = tieuChi3;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public Instant getThoiGianBL() {
        return thoiGianBL;
    }

    public void setThoiGianBL(Instant thoiGianBL) {
        this.thoiGianBL = thoiGianBL;
    }

    public Integer getDiemTNV() {
        return diemTNV;
    }

    public void setDiemTNV(Integer diemTNV) {
        this.diemTNV = diemTNV;
    }

}