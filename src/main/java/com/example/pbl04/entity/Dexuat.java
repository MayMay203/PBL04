package com.example.pbl04.entity;

import com.example.pbl04.entity.Chude;
import com.example.pbl04.entity.Taikhoan;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "dexuat")
public class Dexuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDX", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maTK", nullable = false)
    private Taikhoan maTK;

    @Lob
    @Column(name = "viTri", nullable = false)
    private String viTri;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maChuDe", nullable = false)
    private Chude maChuDe;

    @Lob
    @Column(name = "moTa", nullable = false)
    private String moTa;

    @Column(name = "thoiGianDeXuat")
    private LocalDate thoiGianDeXuat = LocalDate.now();

    @Column(name = "tinhTrangDuyet", nullable = false)
    private Boolean tinhTrangDuyet = false;

    @Column(name = "coXoa", nullable = false)
    private Boolean coXoa = false;

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

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Chude getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(Chude maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getThoiGianDeXuat() {
        return thoiGianDeXuat;
    }

    public void setThoiGianDeXuat(LocalDate thoiGianDeXuat) {
        this.thoiGianDeXuat = thoiGianDeXuat;
    }

    public Boolean getTinhTrangDuyet() {
        return tinhTrangDuyet;
    }

    public void setTinhTrangDuyet(Boolean tinhTrangDuyet) {
        this.tinhTrangDuyet = tinhTrangDuyet;
    }

    public Boolean getCoXoa() {
        return coXoa;
    }

    public void setCoXoa(Boolean coXoa) {
        this.coXoa = coXoa;
    }

}